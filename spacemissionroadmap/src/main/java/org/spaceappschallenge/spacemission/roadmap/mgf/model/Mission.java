package org.spaceappschallenge.spacemission.roadmap.mgf.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mission implements Parcelable {

    private final String title;
    private final String url;
    private final String image;
    private final Date launchDate;
    private final String description;

    public Mission(String title, String url, String image, String description, Date launchDate){
        this.title = title;
        this.url = url;
        this.image = image;
        this.description = description;
        this.launchDate = launchDate;
    }

    public Mission(Parcel in){
        title = in.readString();
        url   = in.readString();
        image = in.readString();
        description = in.readString();
        launchDate = new Date(in.readLong());
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getLaunchDate() {
        return new SimpleDateFormat("MM/dd/yyyy").format(launchDate);
    }

    public String getLaunchTime() {
        return new SimpleDateFormat("hh:mm:ss a").format(launchDate);
    }

    public String getDescription() {
        return description;
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
        parcel.writeLong(launchDate.getTime());
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
