package model.insertions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.viewtables.Deliveries;
import model.viewtables.Enumerations;

import java.sql.Timestamp;

public class DeliveryEnumerations extends Deliveries {
    ObservableList<Enumerations> enumerations;
    String materialsString;

    public DeliveryEnumerations(Integer id, Integer site, Timestamp start, Timestamp end, Integer scheduling) {
        super(id, site, start, end, scheduling);
        enumerations = FXCollections.observableArrayList();
        materialsString = "";
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
