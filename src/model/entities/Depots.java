package model.entities;

public class Depots {
  public Depots(Integer site, Integer number, String capacity, String dimensions) {
    this.site = site;
    this.number = number;
    this.capacity = capacity;
    this.dimensions = dimensions;
  }

  private Integer site;
  private Integer number;
  private String capacity;
  private String dimensions;

  public Integer getSite() {
    return site;
  }

  public void setSite(Integer site) {
    this.site = site;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getCapacity() {
    return capacity;
  }

  public void setCapacity(String capacity) {
    this.capacity = capacity;
  }

  public String getDimensions() {
    return dimensions;
  }

  public void setDimensions(String dimensions) {
    this.dimensions = dimensions;
  }
}
