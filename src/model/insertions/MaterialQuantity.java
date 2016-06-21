package model.insertions;

import model.viewtables.Materials;

public class MaterialQuantity extends Materials {
    private Integer quantity = 0;

    public MaterialQuantity(Integer id, String description, String weight, String dimensions, Integer quantity) {
        super(id, description, weight, dimensions);
        this.quantity = quantity;
    }

    public MaterialQuantity(Materials material) {
        super(material.getId(), material.getDescription(), material.getWeight(), material.getDimension());

    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
