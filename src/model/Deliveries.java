package model;

public class Deliveries {
  private Integer id;
  private String site;
  private java.sql.Date start;
  private java.sql.Date end;
  private String scheduling;
  private String receiver;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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

  public String getScheduling() {
    return scheduling;
  }

  public void setScheduling(String scheduling) {
    this.scheduling = scheduling;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }
}
