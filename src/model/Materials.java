package model;

public class Materials {
  private Integer id;
  private String description;
  private String weight;
  private String dimensions;

  public Materials(Integer id, String description, String weight, String dimensions) {
    this.id = id;
    this.description = description;
    this.weight = weight;
    this.dimensions = dimensions;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getDimensions() {
    return dimensions;
  }

  public void setDimensions(String dimensions) {
    this.dimensions = dimensions;
  }
}
