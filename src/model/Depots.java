package model;

public class Depots {
  private String site;
  private Integer number;
  private String capacity;
  private String dimensions;

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
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
