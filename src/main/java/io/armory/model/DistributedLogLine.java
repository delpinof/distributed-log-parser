package io.armory.model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DistributedLogLine implements Comparable<DistributedLogLine> {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private String logLine;
    private Date date;

    public DistributedLogLine(String logLine) {
        this.logLine = logLine;
        try {
            this.date = sdf.parse(logLine.split(",")[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Date getDate() {
        return date;
    }

    public String getLogLine() {
        return logLine;
    }

    public int compareTo(DistributedLogLine o) {
        return this.getDate().compareTo(o.getDate());
    }

}
