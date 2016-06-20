package model.insertions;

import model.viewtables.Licences;

public class LicencedDeliverer extends Licences {
  private String name;

  public LicencedDeliverer(String deliverer, Integer scheduling, String vehicle, String name) {
    super(deliverer, scheduling, vehicle);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
