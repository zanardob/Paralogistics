package controller.insertions;

import database.CompaniesDAO;
import database.DeliverersDAO;
import database.VehiclesDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Companies;
import model.entities.Deliverers;
import model.entities.Licences;
import model.entities.Vehicles;
import model.special.DeliveryAndEnumerations;
import model.special.LicencedDeliverer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class NewLicenceController implements Initializable{
    private Companies company = null;
    private ObservableList<DeliveryAndEnumerations> deliveries;
    private ObservableList<LicencedDeliverer> licences;

    ObservableList<Deliverers> deliverersList;
    ObservableList<Vehicles> vehiclesList;

    @FXML TableView<Deliverers> DelivererPickerTable;
    @FXML TableColumn<Deliverers, String> DelivererCPF;
    @FXML TableColumn<Deliverers, String> DelivererName;
    @FXML TableColumn<Deliverers, String> DelivererRG;
    @FXML TextField DelivererPickerTextField;

    @FXML TableView<Vehicles> VehiclePickerTable;
    @FXML TableColumn<Vehicles, String> VehiclePlate;
    @FXML TableColumn<Vehicles, java.sql.Date> VehicleConcessionStart;
    @FXML TableColumn<Vehicles, java.sql.Date> VehicleConcessionEnd;
    @FXML TextField VehiclePickerTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deliverersList = null;
        vehiclesList = null;
        try {
            deliverersList = new DeliverersDAO().findAll();
            vehiclesList = new VehiclesDAO().findAll();
            System.out.println(deliverersList);
            System.out.println(vehiclesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DelivererPickerTable.setItems(deliverersList);
        DelivererCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        DelivererName.setCellValueFactory(new PropertyValueFactory<>("name"));
        DelivererRG.setCellValueFactory(new PropertyValueFactory<>("rg"));

        VehiclePickerTable.setItems(vehiclesList);
        VehiclePlate.setCellValueFactory(new PropertyValueFactory<>("plate"));
        VehicleConcessionStart.setCellValueFactory(new PropertyValueFactory<>("concession_start"));
        VehicleConcessionEnd.setCellValueFactory(new PropertyValueFactory<>("concession_end"));
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public void setDeliveries(ObservableList<DeliveryAndEnumerations> deliveries) {
        this.deliveries = deliveries;
    }

    public void setLicences(ObservableList<LicencedDeliverer> licences) {
        this.licences = licences;
    }

    public void Cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) DelivererPickerTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainmenu.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SearchDeliverers(ActionEvent actionEvent) {

    }

    public void SearchVehicles(ActionEvent actionEvent) {

    }

    public void Confirm(ActionEvent actionEvent) {
        Deliverers selectedDeliverer = DelivererPickerTable.getSelectionModel().getSelectedItem();
        Vehicles selectedVehicle = VehiclePickerTable.getSelectionModel().getSelectedItem();
        if((selectedDeliverer == null) || (selectedVehicle == null)) {
            new Alert(Alert.AlertType.ERROR, "Selecione um Entregador E um Veículo").show();
            return;
        }
        Stage stage = (Stage) DelivererPickerTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/insertions/newscheduling2.fxml"));
            Parent root = loader.load();
            licences.add(new LicencedDeliverer(selectedDeliverer.getCpf(), 0, selectedVehicle.getPlate(), selectedDeliverer.getName()));
            NewScheduling2Controller controller = loader.getController();
            controller.setCompany(company);
            controller.setDeliveries(deliveries);
            controller.setLicences(licences);
            controller.refreshTables();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
