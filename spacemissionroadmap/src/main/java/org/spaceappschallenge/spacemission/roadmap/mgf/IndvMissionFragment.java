package org.spaceappschallenge.spacemission.roadmap.mgf;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IndvMissionFragment extends Fragment {


    public static IndvMissionFragment newInstance() {
        IndvMissionFragment fragment = new IndvMissionFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_indv_mission, container, false);
    }
}
