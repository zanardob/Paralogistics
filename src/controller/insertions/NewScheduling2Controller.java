package controller.insertions;

import database.DeliveriesDAO;
import database.EnumerationsDAO;
import database.LicencesDAO;
import database.SchedulingsDAO;
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
import model.insertions.DeliveryEnumerations;
import model.insertions.LicenceDeliverer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class NewScheduling2Controller implements Initializable {
    private Companies company = null;
    private ObservableList<DeliveryEnumerations> deliveries;
    private ObservableList<LicenceDeliverer> licences;

    @FXML
    TableView<LicenceDeliverer> AddLicenceTable;
    @FXML
    TableColumn<LicenceDeliverer, String> DelivererName;
    @FXML
    TableColumn<LicenceDeliverer, String> VehiclePlate;

    @FXML TableView<DeliveryEnumerations> AddDeliveryTable;
    @FXML TableColumn<DeliveryEnumerations, String> Site;
    @FXML TableColumn<DeliveryEnumerations, Timestamp> DeliveryStart;
    @FXML TableColumn<DeliveryEnumerations, Timestamp> DeliveryEnd;
    @FXML TableColumn<DeliveryEnumerations, String> Materials;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DelivererName.setCellValueFactory(new PropertyValueFactory<>("name"));
        VehiclePlate.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        Site.setCellValueFactory(new PropertyValueFactory<>("site"));
        DeliveryStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        DeliveryEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        Materials.setCellValueFactory(new PropertyValueFactory<>("materialsString"));

        refreshTables();
    }

    public void clearTables() {
        licences = FXCollections.observableArrayList();
        deliveries = FXCollections.observableArrayList();
    }

    public void refreshTables() {
        AddLicenceTable.setItems(licences);
        AddDeliveryTable.setItems(deliveries);
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

    public void setDeliveries(ObservableList<DeliveryEnumerations> deliveries) {
        this.deliveries = deliveries;
    }

    public void setLicences(ObservableList<LicenceDeliverer> licences) {
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

        SchedulingsDAO schedulingsDAO = new SchedulingsDAO();

        try {
            // TODO:Error recovery, remove the right things and remove what has to be removed
            scheduling.setId(schedulingsDAO.insertReturnId(scheduling));
            for(LicenceDeliverer licence : licences) {
                Licences newLicence = new Licences(licence.getDeliverer(), scheduling.getId(), licence.getVehicle());
                // INSERT THE newLicence INTO THE DATABASE!
                new LicencesDAO().insert(newLicence);
            }
            for(DeliveryEnumerations delivery : deliveries) {
                Deliveries newDelivery = new Deliveries(0, delivery.getSite(), delivery.getStart(), delivery.getEnd(), scheduling.getId());
                // INSERT THE newDelivery INTO THE DATABASE AND GET THE ID
                // deliveryID = ??? (get from database)

                newDelivery.setId(new DeliveriesDAO().insertReturnId(newDelivery));

                for(Enumerations newEnumeration : delivery.getEnumerations()) {
                    // INSERT THE enumeration INTO THE DATABASE!
                    newEnumeration.setDelivery(newDelivery.getId());
                    new EnumerationsDAO().insert(newEnumeration);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCompany(Companies cpn) {
        this.company = cpn;
    }
}
