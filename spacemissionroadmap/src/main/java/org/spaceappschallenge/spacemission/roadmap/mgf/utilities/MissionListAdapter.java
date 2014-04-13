package org.spaceappschallenge.spacemission.roadmap.mgf.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.spaceappschallenge.spacemission.roadmap.mgf.R;
import org.spaceappschallenge.spacemission.roadmap.mgf.model.Mission;

import java.util.ArrayList;
import java.util.List;

public class MissionListAdapter extends ArrayAdapter<Mission> {

    private List<Mission> postList = new ArrayList<Mission>();

    public MissionListAdapter(Context context, List<Mission> missionList) {
        super(context, R.layout.list_row, missionList);
        this.postList = missionList;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Mission getItem(int position) {
        return postList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.getViewsById(convertView);
            convertView.setTag(holder);
        } else {
            holder  = (ViewHolder) convertView.getTag();
        }

        Mission post = getItem(position);
        holder.setViews(post);

        return convertView;
    }
}