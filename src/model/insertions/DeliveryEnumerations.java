package model.insertions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.viewtables.Deliveries;
import model.viewtables.Enumerations;

import java.sql.Timestamp;

public class DeliveryEnumerations extends Deliveries {
    public String materials_string = "default string";
    protected ObservableList<Enumerations> enumerations;

    public DeliveryEnumerations(Integer id, Integer site, Timestamp start, Timestamp end, Integer scheduling) {
        super(id, site, start, end, scheduling);
        enumerations = FXCollections.observableArrayList();
    }

    public DeliveryEnumerations(Integer id, Integer site, Timestamp start, Timestamp end, Integer scheduling, String materials_string) {
        super(id, site, start, end, scheduling);
        this.materials_string = materials_string;
        enumerations = FXCollections.observableArrayList();
    }


    public DeliveryEnumerations copy() {
        DeliveryEnumerations delivery = new DeliveryEnumerations(this.id, this.site, this.start, this.end, this.scheduling);
        for(Enumerations e : this.enumerations) {
            delivery.addEnumeration(e);
        }
        return delivery;
    }

    public void addEnumeration(Enumerations e) {
        enumerations.add(e);
        if(enumerations.size() > 1) {
            Integer MaterialID = enumerations.get(enumerations.size() - 1).getMaterial();
            // PEGA O NOME DO MATERIAL DO ID ACIMA
            // Materials m = ??? SQL
            // String materialName = m.getName();
            // materials_string = materials_string + ", " + materialName;
            materials_string = materials_string + ", material " + enumerations.get(enumerations.size() - 1).getMaterial();
        }
        else {
            Integer MaterialID = enumerations.get(enumerations.size() - 1).getMaterial();
            // PEGA O NOME DO MATERIAL DO ID ACIMA
            // Materials m = ??? SQL
            // String materialName = m.getName();
            // materials_string = materialName;
            materials_string = "material " + enumerations.get(enumerations.size() - 1).getMaterial();
        }
    }

    public ObservableList<Enumerations> getEnumerations() {
        return enumerations;
    }

    public void clearEnumerations() {
        enumerations.clear();
        materials_string = "";
    }

    public boolean hasEnumerations() {
        return !enumerations.isEmpty();
    }
}
