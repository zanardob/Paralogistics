package model.queries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.insertions.DeliveryEnumerations;
import model.viewtables.Enumerations;

import java.sql.Timestamp;

public class DeliverySchedulingEnumerations extends DeliveryEnumerations {
    String companyCnpj;

    public DeliverySchedulingEnumerations(Integer id, Integer site, Timestamp start, Timestamp end, Integer scheduling) {
        super(id, site, start, end, scheduling);
        enumerations = FXCollections.observableArrayList();
        materialsString = "";
        companyCnpj = "";
    }
}
