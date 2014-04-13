package org.spaceappschallenge.spacemission.roadmap.mgf.utilities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.spaceappschallenge.spacemission.roadmap.mgf.R;
import org.spaceappschallenge.spacemission.roadmap.mgf.model.Mission;

public class ViewHolder {
    private TextView missionTitle;
    private TextView missionLaunchDate;
    private ImageView thumbnailView;

    public void getViewsById(View convertView) {
        missionTitle = (TextView) convertView.findViewById(R.id.mission_title);
        missionLaunchDate = (TextView) convertView.findViewById(R.id.mission_date);
        thumbnailView = (ImageView) convertView.findViewById(R.id.list_image);
    }
    public void setViews(Mission mission) {
        missionTitle.setText(mission.getTitle());
        missionLaunchDate.setText(mission.getLaunchDate().toString());

        String thumbUrl = mission.getImage();

        if (thumbUrl.length() > 1){
            new ImageLoadTask(thumbnailView).execute(thumbUrl);
        }
    }
}