package model.viewtables;

import java.sql.Date;

public class Periods {
  private Integer site;
  private java.sql.Date start;
  private java.sql.Date end;
  private String receiver;

  public Periods(Integer site, Date start, Date end, String receiver) {
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
}
