package model.entities;

import java.sql.Date;

public class Deliveries {
  protected Integer id;
  protected Integer site;
  protected java.sql.Date start;
  protected java.sql.Date end;
  protected Integer scheduling;

  public Deliveries(Integer id, Integer site, Date start, Date end, Integer scheduling) {
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

  public java.sql.Date getStart() {
    return start;
  }

  public void setStart(java.sql.Date start) {
    this.start = start;
  }

  public java.sql.Date getEnd() {
    return end;
  }

  public void setEnd(java.sql.Date end) {
    this.end = end;
  }

  public Integer getScheduling() {
    return scheduling;
  }

  public void setScheduling(Integer scheduling) {
    this.scheduling = scheduling;
  }

}
