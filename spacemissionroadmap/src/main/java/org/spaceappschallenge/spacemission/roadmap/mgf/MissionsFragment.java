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
    private ListView listView;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_missions, container, false);

        TextView textField = (TextView) rootView.findViewById(R.id.text_field);
        listView = (ListView) rootView.findViewById(R.id.mission_list);

        if(category.equals("current")){
            textField.setText("This is current missions");
        }
        else {
            textField.setText("This is past missions");
        }

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

    private List<Mission> getData(){
        return new DataSource().getMissions(category);
    }

}
