package model.special;

import model.entities.Materials;

public class MaterialAndQuantity extends Materials {
  private Integer quantity = 0;

  public MaterialAndQuantity(Integer id, String description, String weight, String dimensions, Integer quantity) {
    super(id, description, weight, dimensions);
    this.quantity = quantity;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
