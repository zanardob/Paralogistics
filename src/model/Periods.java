package model;

public class Periods {
  private String site;
  private java.sql.Date start;
  private java.sql.Date end;

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
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
