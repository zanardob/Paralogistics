package model.viewtables;

public class Companies {
  private String cnpj;
  private String name;
  private String fantasy;

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public Companies(String cnpj, String name, String fantasy) {
    this.cnpj = cnpj;
    this.name = name;
    this.fantasy = fantasy;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFantasy() {
    return fantasy;
  }

  public void setFantasy(String fantasy) {
    this.fantasy = fantasy;
  }
}
