package model.viewtables;

import java.sql.Timestamp;

public class Periods {
    private Integer site;
    private Timestamp start;
    private Timestamp end;
    private String receiver;

    public Periods(Integer site, Timestamp start, Timestamp end, String receiver) {
        this.site = site;
        this.start = start;
        this.end = end;
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getSite() {
        return site;
    }

    public void setSite(Integer site) {
        this.site = site;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}
