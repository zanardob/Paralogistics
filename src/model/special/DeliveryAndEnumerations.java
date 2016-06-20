package model.special;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entities.Deliveries;
import model.entities.Enumerations;

import java.sql.Timestamp;

public class DeliveryAndEnumerations extends Deliveries {
    ObservableList<Enumerations> enumerations;
    String materialsString;

    public DeliveryAndEnumerations(Integer id, Integer site, Timestamp start, Timestamp end, Integer scheduling) {
        super(id, site, start, end, scheduling);
        enumerations = FXCollections.observableArrayList();
        materialsString = "";
    }

    public DeliveryAndEnumerations copy() {
        DeliveryAndEnumerations delivery = new DeliveryAndEnumerations(this.id, this.site, this.start, this.end, this.scheduling);
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
            // materialsString = materialsString + ", " + materialName;
            materialsString = materialsString + ", material " + enumerations.get(enumerations.size() - 1).getMaterial();
        }
        else {
            Integer MaterialID = enumerations.get(enumerations.size() - 1).getMaterial();
            // PEGA O NOME DO MATERIAL DO ID ACIMA
            // Materials m = ??? SQL
            // String materialName = m.getName();
            // materialsString = materialName;
            materialsString = "material " + enumerations.get(enumerations.size() - 1).getMaterial();
        }
    }

    public ObservableList<Enumerations> getEnumerations() {
        return enumerations;
    }

    public void clearEnumerations() {
        enumerations.clear();
        materialsString = "";
    }

    public boolean hasEnumerations() {
        return !enumerations.isEmpty();
    }
}
