package model.viewtables;

import java.sql.Timestamp;

public class Deliveries {
    protected Integer id;
    protected Integer site;
    protected Timestamp start;
    protected Timestamp end;
    protected Integer scheduling;

    public Deliveries(Integer id, Integer site, Timestamp start, Timestamp end, Integer scheduling) {
        this.id = id;
        this.site = site;
        this.start = start;
        this.end = end;
        this.scheduling = scheduling;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getScheduling() {
        return scheduling;
    }

    public void setScheduling(Integer scheduling) {
        this.scheduling = scheduling;
    }

}
