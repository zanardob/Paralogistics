package model;

public class Supplies {
  private Integer material;
  private String company;

  public Supplies(Integer material, String company) {
    this.material = material;
    this.company = company;
  }

  public Integer getMaterial() {
    return material;
  }

  public void setMaterial(Integer material) {
    this.material = material;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }
}
