package model.viewtables;

public class Enumerations {
  private Integer delivery;
  private Integer material;
  private Integer quantity;

  public Enumerations(Integer delivery, Integer material, Integer quantity) {
    this.delivery = delivery;
    this.material = material;
    this.quantity = quantity;
  }

  public Integer getDelivery() {
    return delivery;
  }

  public void setDelivery(Integer delivery) {
    this.delivery = delivery;
  }

  public Integer getMaterial() {
    return material;
  }

  public void setMaterial(Integer material) {
    this.material = material;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
