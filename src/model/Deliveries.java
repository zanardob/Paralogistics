package model;

public class Deliveries {
  private Integer id;
  private Integer site;
  private java.sql.Date start;
  private java.sql.Date end;
  private Integer scheduling;
  private String receiver;

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

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }
}
