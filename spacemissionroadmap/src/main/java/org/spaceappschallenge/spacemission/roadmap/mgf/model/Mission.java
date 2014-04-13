package org.spaceappschallenge.spacemission.roadmap.mgf.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class Mission implements Parcelable {

    public String title;
    public String url;
    public String image;
    public MissionEventDate launchDate;
    public String description;
    public String summary;
    public MissionDate date;

    public Mission(){}

    public Mission(Parcel in){
        title = in.readString();
        url   = in.readString();
        image = in.readString();
        description = in.readString();

        launchDate = new MissionEventDate();

        launchDate.start = new DateTime(in.readLong());
        launchDate.end = new DateTime((in.readLong()));

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

        convertDate();

        parcel.writeLong(launchDate.start.getMillis());
        parcel.writeLong(launchDate.end.getMillis());

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

    public void convertDate(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        launchDate = new MissionEventDate();

        if (date != null) {
            launchDate.start = dateTimeFormatter.parseDateTime(date.start);
            launchDate.end = dateTimeFormatter.parseDateTime(date.end);
        } else {
            launchDate.start = new DateTime();
            launchDate.end = new DateTime();
        }

    }

    public static class MissionDate {
        public String start;
        public String end;
    }

}
