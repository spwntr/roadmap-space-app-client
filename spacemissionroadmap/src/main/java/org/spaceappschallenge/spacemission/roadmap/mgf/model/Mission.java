package org.spaceappschallenge.spacemission.roadmap.mgf.model;

import java.util.Date;

public class Mission {

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

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public String getDescription() {
        return description;
    }
}
