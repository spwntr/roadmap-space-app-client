package org.spaceappschallenge.spacemission.roadmap.mgf;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.spaceappschallenge.spacemission.roadmap.mgf.model.Mission;
import org.spaceappschallenge.spacemission.roadmap.mgf.service.DataSource;
import org.spaceappschallenge.spacemission.roadmap.mgf.utilities.MissionListAdapter;

import java.util.List;

public class MissionsFragment extends Fragment {

    private String category;
    private ListView listView;
    private String actionbarTitle;

    public static MissionsFragment newInstance(String category) {
        MissionsFragment fragment = new MissionsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        fragment.setArguments(bundle);
        return fragment;
    }
    public MissionsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString("category");
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
                String url = ((Mission) listView.getItemAtPosition(position)).url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        return rootView;
    }

    private List<Mission> getData(){
        return new DataSource().getMissions(category);
    }

}
