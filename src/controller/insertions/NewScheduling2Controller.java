package controller.insertions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import database.DeliveriesDAO;
import database.EnumerationsDAO;
import database.LicencesDAO;
import database.SchedulingsDAO;

import model.insertions.DeliveryEnumerations;
import model.insertions.LicenceDeliverer;
import model.viewtables.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;


public class NewScheduling2Controller implements Initializable {
    @FXML TableView<LicenceDeliverer> AddLicenceTable;
    @FXML TableColumn<LicenceDeliverer, String> DelivererName;
    @FXML TableColumn<LicenceDeliverer, String> VehiclePlate;

    @FXML TableView<DeliveryEnumerations> AddDeliveryTable;
    @FXML TableColumn<DeliveryEnumerations, String> Site;
    @FXML TableColumn<DeliveryEnumerations, Timestamp> DeliveryStart;
    @FXML TableColumn<DeliveryEnumerations, Timestamp> DeliveryEnd;

    @FXML Button AddDeliveryButton;
    @FXML Button AddLicenceButton;
    @FXML Button CancelButton;
    @FXML Button ConfirmButton;

    private Companies company = null;
    private ObservableList<DeliveryEnumerations> deliveries;
    private ObservableList<LicenceDeliverer> licences;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DelivererName.setCellValueFactory(new PropertyValueFactory<>("name"));
        VehiclePlate.setCellValueFactory(new PropertyValueFactory<>("vehicle"));

        Site.setCellValueFactory(new PropertyValueFactory<>("site"));
        DeliveryStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        DeliveryEnd.setCellValueFactory(new PropertyValueFactory<>("end"));

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
        LicencesDAO licencesDAO = new LicencesDAO();
        DeliveriesDAO deliveriesDAO = new DeliveriesDAO();
        Integer licencesCount = 0;
        Integer deliveriesCount = 0;
        Integer enumerationsCount;

        try {
            //Inserts the scheduling and gets the "Id" generated by the database
            scheduling.setId(schedulingsDAO.insertReturnId(scheduling));
            System.out.println("New Scheduling inserted, ID = " + scheduling.getId());
            licencesCount = licences.size();
            if (licencesCount > 0) {
                for (LicenceDeliverer licence : licences) {
                    Licences newLicence = new Licences(licence.getDeliverer(), scheduling.getId(), licence.getVehicle());
                    licencesDAO.insert(newLicence);
                    System.out.println("New Licence inserted!");
                }
            }
            else {
                //If no licences were added the scheduling is removed
                schedulingsDAO.deleteById(scheduling.getId());
                System.out.println("Scheduling " + scheduling.getId() + " should be removed (no licences added)");
                return;
            }
            deliveriesCount = deliveries.size();
            if (deliveriesCount > 0) {
                for (DeliveryEnumerations delivery : deliveries) {
                    Deliveries newDelivery = new Deliveries(0, delivery.getSite(), delivery.getStart(), delivery.getEnd(), scheduling.getId());
                    newDelivery.setId(deliveriesDAO.insertReturnId(newDelivery));
                    System.out.println("New Delivery inserted, ID = " + newDelivery.getId());
                    enumerationsCount = delivery.getEnumerations().size();
                    if (enumerationsCount > 0) {
                        for (Enumerations newEnumeration : delivery.getEnumerations()) {
                            newEnumeration.setDelivery(newDelivery.getId());
                            new EnumerationsDAO().insert(newEnumeration);
                            System.out.println("New Enumeration inserted!");
                        }
                    }
                    else {
                        //If no "Enumerations" are added the scheduling is removed and the data related to ir cascades
                        schedulingsDAO.deleteById(scheduling.getId());
                        System.out.println("Scheduling " + scheduling.getId() + " should be removed (no enumerations in delivery " + newDelivery.getId() + " added)");
                        return;
                    }
                }
            }
            else {
                //If no "Deliveries" are added the scheduling is removed and the data related to it cascades
                schedulingsDAO.deleteById(scheduling.getId());
                System.out.println("Scheduling " + scheduling.getId() + " should be removed (no deliveries added)");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) AddLicenceTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainmenu.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCompany(Companies cpn) {
        this.company = cpn;
    }
}
