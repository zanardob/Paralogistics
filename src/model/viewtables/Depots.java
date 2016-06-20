package model.viewtables;

public class Depots {
  private Integer site;
  private Integer number;
  private String capacity;
  private String dimension;

  public Depots(Integer site, Integer number, String capacity, String dimension) {
    this.site = site;
    this.number = number;
    this.capacity = capacity;
    this.dimension = dimension;
  }

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

  public String getDimension() {
    return dimension;
  }

  public void setDimension(String dimension) {
    this.dimension = dimension;
  }
}
