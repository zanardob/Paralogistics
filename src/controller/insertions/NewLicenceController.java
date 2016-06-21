package controller.insertions;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

import database.DeliverersDAO;
import database.VehiclesDAO;

import model.insertions.DeliveryEnumerations;
import model.insertions.LicenceDeliverer;
import model.viewtables.Companies;
import model.viewtables.Deliverers;
import model.viewtables.Vehicles;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class NewLicenceController implements Initializable {
    ObservableList<Deliverers> deliverersList;
    ObservableList<Vehicles> vehiclesList;

    @FXML TableView<Deliverers> DelivererPickerTable;
    @FXML TableColumn<Deliverers, String> DelivererCPF;
    @FXML TableColumn<Deliverers, String> DelivererName;
    @FXML TableColumn<Deliverers, String> DelivererRG;

    @FXML TableView<Vehicles> VehiclePickerTable;
    @FXML TableColumn<Vehicles, String> VehiclePlate;
    @FXML TableColumn<Vehicles, Timestamp> VehicleConcessionStart;
    @FXML TableColumn<Vehicles, Timestamp> VehicleConcessionEnd;

    @FXML TextField DelivererPickerTextField;
    @FXML TextField VehiclePickerTextField;

    private Companies company = null;
    private ObservableList<DeliveryEnumerations> deliveries;
    private ObservableList<LicenceDeliverer> licences;

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

        FilteredList<Deliverers> filteredDeliverers = new FilteredList<>(deliverersList, d -> true);
        FilteredList<Vehicles> filteredVehicles = new FilteredList<>(vehiclesList, v -> true);

        SortedList<Deliverers> sortedDeliverers = new SortedList<>(filteredDeliverers);
        SortedList<Vehicles> sortedVehicles = new SortedList<>(filteredVehicles);

        sortedDeliverers.comparatorProperty().bind(DelivererPickerTable.comparatorProperty());
        sortedVehicles.comparatorProperty().bind(VehiclePickerTable.comparatorProperty());

        DelivererPickerTextField.textProperty().addListener((observable, oldValue, newValue) ->
            filteredDeliverers.setPredicate(deliverer -> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String delivererFilter = newValue.toLowerCase();
                return deliverer.getCpf().contains(delivererFilter) || deliverer.getName().toLowerCase().contains(delivererFilter) || deliverer.getRg().contains(delivererFilter);
            })
        );

        VehiclePickerTextField.textProperty().addListener((observable, oldValue, newValue) ->
            filteredVehicles.setPredicate(vehicle -> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String vehicleFilter = newValue.toLowerCase();
                return vehicle.getPlate().toLowerCase().contains(vehicleFilter);
            })
        );

        DelivererPickerTable.setItems(sortedDeliverers);
        DelivererCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        DelivererName.setCellValueFactory(new PropertyValueFactory<>("name"));
        DelivererRG.setCellValueFactory(new PropertyValueFactory<>("rg"));

        VehiclePickerTable.setItems(sortedVehicles);
        VehiclePlate.setCellValueFactory(new PropertyValueFactory<>("plate"));
        VehicleConcessionStart.setCellValueFactory(new PropertyValueFactory<>("concession_start"));
        VehicleConcessionEnd.setCellValueFactory(new PropertyValueFactory<>("concession_end"));
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public void setDeliveries(ObservableList<DeliveryEnumerations> deliveries) {
        this.deliveries = deliveries;
    }

    public void setLicences(ObservableList<LicenceDeliverer> licences) {
        this.licences = licences;
    }

    public void Cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) DelivererPickerTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/insertions/newscheduling2.fxml"));
            Parent root = loader.load();
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

    public void SearchDeliverers(ActionEvent actionEvent) {

    }

    public void SearchVehicles(ActionEvent actionEvent) {

    }

    public void Confirm(ActionEvent actionEvent) {
        Deliverers selectedDeliverer = DelivererPickerTable.getSelectionModel().getSelectedItem();
        Vehicles selectedVehicle = VehiclePickerTable.getSelectionModel().getSelectedItem();
        if ((selectedDeliverer == null) || (selectedVehicle == null)) {
            new Alert(Alert.AlertType.ERROR, "Selecione um Entregador E um Veículo").show();
            return;
        }
        Stage stage = (Stage) DelivererPickerTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/insertions/newscheduling2.fxml"));
            Parent root = loader.load();
            licences.add(new LicenceDeliverer(selectedDeliverer.getCpf(), 0, selectedVehicle.getPlate(), selectedDeliverer.getName()));
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
