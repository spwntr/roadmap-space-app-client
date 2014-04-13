package org.spaceappschallenge.spacemission.roadmap.mgf.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Mission implements Parcelable {

    public String title;
    public String url;
    public String image;
    public MissionEventDate launchDate;
    public String description;
    public String summary;

    public Mission(){}

    public Mission(Parcel in){
        title = in.readString();
        url   = in.readString();
        image = in.readString();
        description = in.readString();
        launchDate.start = new Date(in.readLong());
        launchDate.end = new Date((in.readLong()));
        summary = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeString(url);
        parcel.writeString(image);
        parcel.writeString(description);
        parcel.writeLong(launchDate.start.getTime());
        parcel.writeLong(launchDate.end.getTime());
        parcel.writeString(summary);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Mission createFromParcel(Parcel in) {
            return new Mission(in);
        }

        public Mission[] newArray(int size) {
            return new Mission[size];
        }
    };

}
