package model.viewtables;

public class Materials {
  protected Integer id;
  protected String description;
  protected String weight;
  protected String dimension;

  public Materials(Integer id, String description, String weight, String dimension) {
    this.id = id;
    this.description = description;
    this.weight = weight;
    this.dimension = dimension;
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

  public String getDimension() {
    return dimension;
  }

  public void setDimension(String dimension) {
    this.dimension = dimension;
  }
}
