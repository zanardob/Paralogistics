package model.viewtables;

public class Schedulings {
  private Integer id;
  private String company;

  public Schedulings(Integer id, String company) {
    this.id = id;
    this.company = company;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }
}
