package org.spaceappschallenge.spacemission.roadmap.mgf;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.spaceappschallenge.spacemission.roadmap.mgf.model.Mission;
import org.spaceappschallenge.spacemission.roadmap.mgf.model.Missions;
import org.spaceappschallenge.spacemission.roadmap.mgf.network.SpaceMissionRoadmapAPIClient;
import org.spaceappschallenge.spacemission.roadmap.mgf.utilities.CustomProgressDialog;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getSimpleName();


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Button btn_current_missions = (Button) rootView.findViewById(R.id.btn_current_missions);
        btn_current_missions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String category = "current";

                SpaceMissionRoadmapAPIClient apiClient = ((MainActivity) getActivity()).getAPIClient();

                final CustomProgressDialog.CustomizedDialog dialog = CustomProgressDialog.generateDialog(getActivity());

                dialog.show();

                apiClient.getCurrentMissionData(new Callback<Missions>() {
                    @Override
                    public void success(Missions missions, Response response) {//TODO: persist data

                        dialog.dismiss();


                        setFragment(category, missions.missions);
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

            }
        });

        Button btn_previous_missions = (Button) rootView.findViewById(R.id.btn_previous_missions);
        btn_previous_missions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "previous";
                setFragment(category, null);
            }
        });

        return rootView;
    }

    private void setFragment(final String category, final List<Mission> missions) {

        Fragment switchToThisFragment = MissionsFragment.newInstance(category, missions);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, switchToThisFragment)
                .commitAllowingStateLoss();//TODO: should not perform transaction after network call as activity's state may have changed
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getActionBar().setTitle(getString(R.string.app_name));
    }
}
