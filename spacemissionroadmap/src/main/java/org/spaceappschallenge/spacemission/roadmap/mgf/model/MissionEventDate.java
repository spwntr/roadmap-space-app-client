package org.spaceappschallenge.spacemission.roadmap.mgf.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by lfeliu on 4/13/14.
 */
public class MissionEventDate {

    public DateTime start;
    public DateTime end;

    public String startToString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("M/dd/yyyy");
        return start.toString(dateTimeFormatter);
    }

    public String endToString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("M/dd/yyyy");
        return end.toString(dateTimeFormatter);
    }
}
