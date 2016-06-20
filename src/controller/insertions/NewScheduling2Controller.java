package controller.insertions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.viewtables.*;
import model.insertions.DeliveryAndEnumerations;
import model.insertions.LicencedDeliverer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class NewScheduling2Controller implements Initializable {
    private Companies company = null;
    private ObservableList<DeliveryAndEnumerations> deliveries;
    private ObservableList<LicencedDeliverer> licences;

    @FXML TableView<LicencedDeliverer> AddLicenceTable;
    @FXML TableColumn<LicencedDeliverer, String> DelivererName;
    @FXML TableColumn<LicencedDeliverer, String> VehiclePlate;

    @FXML TableView<DeliveryAndEnumerations> AddDeliveryTable;
    @FXML TableColumn<DeliveryAndEnumerations, String> Site;
    @FXML TableColumn<DeliveryAndEnumerations, java.sql.Date> DeliveryStart;
    @FXML TableColumn<DeliveryAndEnumerations, java.sql.Date> DeliveryEnd;
    @FXML TableColumn<DeliveryAndEnumerations, String> Materials;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTables();
    }

    public void clearTables() {
        licences = FXCollections.observableArrayList();
        deliveries = FXCollections.observableArrayList();
    }

    public void refreshTables() {
        AddLicenceTable.setItems(licences);
        DelivererName.setCellValueFactory(new PropertyValueFactory<>("name"));
        VehiclePlate.setCellValueFactory(new PropertyValueFactory<>("vehicle"));

        AddDeliveryTable.setItems(deliveries);
        Site.setCellValueFactory(new PropertyValueFactory<>("site"));
        DeliveryStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        DeliveryEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        Materials.setCellValueFactory(new PropertyValueFactory<>("materialsString"));
    }

    public void AddLicence(ActionEvent actionEvent) {
        Stage stage = (Stage) AddLicenceTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/insertions/newlicence.fxml"));
            Parent root = loader.load();
            NewLicenceController controller = loader.getController();
            controller.setCompany(company);
            controller.setDeliveries(deliveries);
            controller.setLicences(licences);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddDelivery(ActionEvent actionEvent) {
        Stage stage = (Stage) AddLicenceTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/insertions/newdelivery.fxml"));
            Parent root = loader.load();
            NewDeliveryController controller = loader.getController();
            controller.setCompany(company);
            controller.setDeliveries(deliveries);
            controller.setLicences(licences);
            controller.fillMaterialsTable();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDeliveries(ObservableList<DeliveryAndEnumerations> deliveries) {
        this.deliveries = deliveries;
    }

    public void setLicences(ObservableList<LicencedDeliverer> licences) {
        this.licences = licences;
    }

    public void Cancel(ActionEvent actionEvent) {
        deliveries.clear();
        Stage stage = (Stage) AddLicenceTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainmenu.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Confirm(ActionEvent actionEvent) {
        Schedulings scheduling = new Schedulings(0, company.getCnpj());
        Integer schedulingID = scheduling.getId();
        // NOW YOU HAVE A SCHEDULING
        // INSERT IT INTO THE DATABASE AND GET THE ID
        // schedulingID = ??? (get from database)

        for(LicencedDeliverer licence : licences) {
            Licences newLicence = new Licences(licence.getDeliverer(), schedulingID, licence.getVehicle());
            // INSERT THE newLicence INTO THE DATABASE!
        }
        for(DeliveryAndEnumerations delivery : deliveries) {
            Deliveries newDelivery = new Deliveries(0, delivery.getSite(), delivery.getStart(), delivery.getEnd(), schedulingID);
            Integer deliveryID = newDelivery.getId();
            // INSERT THE newDelivery INTO THE DATABASE AND GET THE ID
            // deliveryID = ??? (get from database)
            for(Enumerations newEnumeration : delivery.getEnumerations()) {
                newEnumeration.setDelivery(deliveryID);
                // INSERT THE enumeration INTO THE DATABASE!
            }
        }
    }

    public void setCompany(Companies cpn) {
        this.company = cpn;
    }
}
