package org.spaceappschallenge.spacemission.roadmap.mgf;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.spaceappschallenge.spacemission.roadmap.mgf.model.Mission;

public class IndvMissionFragment extends Fragment {
    Mission mission;

    public static IndvMissionFragment newInstance(Mission clickedMission) {
        IndvMissionFragment fragment = new IndvMissionFragment();
        fragment.mission = clickedMission;
        return fragment;
    }
    public IndvMissionFragment() {
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
        View rootView =  inflater.inflate(R.layout.fragment_indv_mission, container, false);

        ((TextView) rootView.findViewById(R.id.mission_title)).setText(mission.title);
        ((TextView) rootView.findViewById(R.id.mission_start)).setText(mission.launchDate.startToString());
        ((TextView) rootView.findViewById(R.id.mission_end)).setText(mission.launchDate.endToString());
        ((TextView) rootView.findViewById(R.id.description)).setText(mission.description);

        return rootView;
    }
}
