package org.spaceappschallenge.spacemission.roadmap.mgf;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.spaceappschallenge.spacemission.roadmap.mgf.model.Mission;

public class IndividualMissionFragment extends Fragment {
    Mission mission;

    public static IndividualMissionFragment newInstance(Mission clickedMission) {
        IndividualMissionFragment fragment = new IndividualMissionFragment();
        fragment.mission = clickedMission;
        return fragment;
    }
    public IndividualMissionFragment() {
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

        if(mission != null && mission.image != null){
            ImageView imageView = ((ImageView) rootView.findViewById(R.id.imageView));
            Picasso.with(getActivity()).load(mission.image).fit().into(imageView);
        }
        ((TextView) rootView.findViewById(R.id.mission_title)).setText(mission.title);
        ((TextView) rootView.findViewById(R.id.mission_start)).setText(mission.launchDate.startToString());
        ((TextView) rootView.findViewById(R.id.mission_end)).setText(mission.launchDate.endToString());
        ((TextView) rootView.findViewById(R.id.description)).setText(mission.description);
        Button button = ((Button) rootView.findViewById(R.id.btn_url));


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mission.url));
                startActivity(browserIntent);
            }
        });

        return rootView;
    }
}
