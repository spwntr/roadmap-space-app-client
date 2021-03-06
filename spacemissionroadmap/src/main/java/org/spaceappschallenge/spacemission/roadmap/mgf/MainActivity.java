package org.spaceappschallenge.spacemission.roadmap.mgf;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.spaceappschallenge.spacemission.roadmap.mgf.model.Missions;
import org.spaceappschallenge.spacemission.roadmap.mgf.network.SpaceMissionRoadmapAPIClient;
import org.spaceappschallenge.spacemission.roadmap.mgf.utilities.Constants;
import org.spaceappschallenge.spacemission.roadmap.mgf.utilities.CustomProgressDialog;

import java.io.IOException;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String PREFERENCES_PROPERTY_REG_ID = "registration_id";
    private static final String PREFERENCES_PROPERTY_APP_VERSION = "appVersion";

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private static SpaceMissionRoadmapAPIClient apiClient;

    private GoogleCloudMessaging gcm;
    private String SENDER_ID = "171639698085";
    private String regid;
    private Context context;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        // Check device for Play Services APK. If check succeeds, proceed with GCM registration.
        if (checkPlayServices()) {

            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);

            if (regid.isEmpty()) {
                registerInBackground();
            }

        } else {
            Log.i(TAG, "No valid Google Play Services APK found.");
        }

        setAPIClient(null);

    }

    @Override
    public void onNavigationDrawerItemSelected(int index) {
        // update the main content by replacing fragments

        int menuItemPosition = index + 1;

        Fragment switchToThisFragment = getProperFragmentForDrawerSelection(menuItemPosition);

        if (menuItemPosition == 2) {

            final CustomProgressDialog.CustomizedDialog dialog = CustomProgressDialog.generateDialog(this);

            dialog.show();

            apiClient.getCurrentMissionData(new Callback<Missions>() {
                @Override
                public void success(Missions missions, Response response) {//TODO: persist data

                    dialog.dismiss();


                    Fragment switchToThisFragment = MissionsFragment.newInstance("current", missions.missions);

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.container, switchToThisFragment)
                            .commitAllowingStateLoss();//TODO: should not perform transaction after network call as activity's state may have changed
                }

                @Override
                public void failure(RetrofitError error) {//TODO: better error handling

                    dialog.dismiss();

                    Log.e(TAG, "Could not retrieve current missions from server.");

                    if (error != null){
                        Log.e(TAG, error.toString());
                    }
                }

            });
        } else{
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, switchToThisFragment)
                    .commitAllowingStateLoss();
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.missions);
                break;
            case 2:
                mTitle = getString(R.string.current_missions);
                break;
            case 3:
                mTitle = getString(R.string.past_missions);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    private Fragment getProperFragmentForDrawerSelection(int position) {
        if (position == 1) {
            return HomeFragment.newInstance();
        } else {
            String arg = position == 2 ? "current" : "previous";
            return MissionsFragment.newInstance(arg, null);//TODO:
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Check device for Play Services APK.
        checkPlayServices();
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");//TODO: Show dialog to user
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Stores the registration ID and the app versionCode in the application's
     * {@code SharedPreferences}.
     *
     * @param context application's context.
     * @param regId registration ID
     */
    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGcmPreferences();
        int appVersion = getAppVersion(context);
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCES_PROPERTY_REG_ID, regId);
        editor.putInt(PREFERENCES_PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }

    /**
     * Gets the current registration ID for application on GCM service, if there is one.
     * <p>
     * If result is empty, the app needs to register.
     *
     * @return registration ID, or empty string if there is no existing
     *         registration ID.
     */
    private String getRegistrationId(final Context context) {

        final SharedPreferences prefs = getGcmPreferences();

        final String registrationId = prefs.getString(PREFERENCES_PROPERTY_REG_ID, "");

        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }

        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.

        final int registeredVersion = prefs.getInt(PREFERENCES_PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        final int currentVersion = getAppVersion(context);

        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }

        return registrationId;

    }

    /**
     * Registers the application with GCM servers asynchronously.
     * <p>
     * Stores the registration ID and the app versionCode in the application's
     * shared preferences.
     */
    private void registerInBackground() {

        final Runnable registrationRunnable = new Runnable() {
            @Override
            public void run() {
                try {

                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }

                    regid = gcm.register(SENDER_ID);

                    Log.i(TAG, "Device registered, registration ID=" + regid);

                    // Send the registration ID to SERVER
                    sendRegistrationIdToBackend();

                    // Persist the regID - no need to register again.
                    storeRegistrationId(context, regid);

                } catch (IOException ex) {
                   Log.e(TAG, "Error :" + ex.getMessage());
                    //TODO: If there is an error,require user action.
                }
            }
        };

        final Thread registrationThread = new Thread(registrationRunnable);
        registrationThread.setName("Space Apps Challenge GCM Registration Thread");
        registrationThread.start();

    }

    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    private static int getAppVersion(final Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    private SharedPreferences getGcmPreferences() {
        return getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

    /**
     * Sends the registration ID to SERVER over HTTP, so it can use GCM/HTTP or CCS to send
     * messages to the app.
     */
    private void sendRegistrationIdToBackend() {
        apiClient.sendRegistrationId(this.regid, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Log.i(TAG, "Registration id sent to server.");
            }

            @Override
            public void failure(RetrofitError error) {//TODO: Add better error handling

                Log.e(TAG, "Error registering id with server.");

                if (error != null){
                    Log.e(TAG, error.toString());
                }

            }
        });
    }

    public void setAPIClient(final RestAdapter restAdapter){

        if(restAdapter == null){

            RestAdapter.Builder builder = new RestAdapter.Builder();
            builder.setEndpoint(Constants.SERVER);
            RestAdapter newRestAdapter = builder.build();

            apiClient = newRestAdapter.create(SpaceMissionRoadmapAPIClient.class);

        } else {
            apiClient = (SpaceMissionRoadmapAPIClient) restAdapter;
        }

    }

    public SpaceMissionRoadmapAPIClient getAPIClient(){
        return apiClient;
    }

}
