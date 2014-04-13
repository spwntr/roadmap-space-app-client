package org.spaceappschallenge.spacemission.roadmap.mgf;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.spaceappschallenge.spacemission.roadmap.mgf.model.Mission;
import org.spaceappschallenge.spacemission.roadmap.mgf.service.DataSource;
import org.spaceappschallenge.spacemission.roadmap.mgf.utilities.MissionListAdapter;

import java.util.List;

public class MissionsFragment extends Fragment {
    private String category;

    public static MissionsFragment newInstance(String category) {
        MissionsFragment fragment = new MissionsFragment();
        fragment.category = category;
        return fragment;
    }
    public MissionsFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_missions, container, false);
        TextView textField = (TextView) rootView.findViewById(R.id.text_field);
        if(category.equals("current")){
            textField.setText("This is current missions");
        }
        else {
            textField.setText("This is past missions");
        }

        displayMissions(rootView);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void displayMissions(View view) {
        ListView listView = (ListView) view.findViewById(R.id.mission_list);
        listView.setAdapter(new MissionListAdapter(this.getActivity().getBaseContext(), getData()));
    }

    private List<Mission> getData(){
        return new DataSource().getMissions(category);
    }

}
