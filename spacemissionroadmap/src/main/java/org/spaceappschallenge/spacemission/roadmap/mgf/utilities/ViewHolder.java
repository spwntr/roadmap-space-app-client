package org.spaceappschallenge.spacemission.roadmap.mgf.utilities;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.spaceappschallenge.spacemission.roadmap.mgf.R;
import org.spaceappschallenge.spacemission.roadmap.mgf.model.Mission;

public class ViewHolder {
    private TextView missionTitle;
    private TextView missionLaunchDate;
    private ImageView thumbnailView;

    public void getViewsById(final View convertView) {
        missionTitle = (TextView) convertView.findViewById(R.id.mission_title);
        missionLaunchDate = (TextView) convertView.findViewById(R.id.mission_date);
        thumbnailView = (ImageView) convertView.findViewById(R.id.list_image);
    }
    public void setViews(final Mission mission, final Context context) {
        missionTitle.setText(mission.getTitle());
        missionLaunchDate.setText(mission.getLaunchDate().toString());

       final String thumbUrl = mission.getImage();

        if (thumbUrl != null && !thumbUrl.isEmpty() && thumbUrl.length() > 1){
            Picasso.with(context).load(thumbUrl).skipMemoryCache().fit().into(thumbnailView);
        }
    }
}