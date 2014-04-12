package org.spaceappschallenge.spacemission.roadmap.mgf;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {


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
                Fragment switchToThisFragment = MissionsFragment.newInstance("current");

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, switchToThisFragment)
                        .commit();
            }
        });

        Button btn_previous_missions = (Button) rootView.findViewById(R.id.btn_previous_missions);
        btn_previous_missions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment switchToThisFragment = MissionsFragment.newInstance("previous");

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, switchToThisFragment)
                        .commit();
            }
        });

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

}
