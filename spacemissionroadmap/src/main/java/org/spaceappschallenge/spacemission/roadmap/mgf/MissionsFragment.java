package org.spaceappschallenge.spacemission.roadmap.mgf;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.spaceappschallenge.spacemission.roadmap.mgf.model.Mission;
import org.spaceappschallenge.spacemission.roadmap.mgf.service.DataSource;
import org.spaceappschallenge.spacemission.roadmap.mgf.utilities.MissionListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MissionsFragment extends Fragment {

    private String category;
    private ListView listView;
    private String actionbarTitle;
    private List<Mission> missionList = new ArrayList<Mission>();

    public static MissionsFragment newInstance(String category, List<Mission> missionList) {
        MissionsFragment fragment = new MissionsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putParcelableArrayList("missions", (ArrayList<Mission>) missionList);
        fragment.setArguments(bundle);
        return fragment;
    }
    public MissionsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle bundle = getArguments();

        if (bundle != null) {

            category = bundle.getString("category");
            missionList = bundle.getParcelableArrayList("missions");

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        listView.setAdapter(new MissionListAdapter(this.getActivity().getBaseContext(), getData()));
        getActivity().getActionBar().setTitle(actionbarTitle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_missions, container, false);

        listView = (ListView) rootView.findViewById(R.id.mission_list);

        if(category.equals("current")){
            actionbarTitle = "Current Missions";
        }
        else {
            actionbarTitle = "Past Missions";
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Mission clickedMission = (Mission) listView.getItemAtPosition(position);
                Fragment switchToThisFragment = new IndvMissionFragment().newInstance(clickedMission);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, switchToThisFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });

        return rootView;
    }

    private List<Mission> getData(){

        final List<Mission> missions = new ArrayList<Mission>();

       if(!category.equalsIgnoreCase("current") || missionList == null){
           missions.addAll(new DataSource().getMissions(category));//TODO: Change to fetch actual data. Returns fake data for past missions for now.
       } else {
           missions.addAll(missionList);
       }

        return missions;

    }

}
