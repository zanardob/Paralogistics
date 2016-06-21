package controller.queries;

import database.DeliveriesDAO;
import database.LicencesDAO;
import database.SchedulingsDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.insertions.LicenceDeliverer;
import model.viewtables.Companies;
import model.viewtables.Deliverers;
import model.viewtables.Deliveries;
import model.viewtables.Schedulings;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 18/06/2016.
 */
public class QuerySchedulingsController implements Initializable{
    ObservableList<Schedulings> schedulingsList = null;
    ObservableList<Deliveries> deliveriesList = null;
    ObservableList<LicenceDeliverer> licenceDeliverersList = null;

    @FXML TableView<Schedulings> SchedulingPickerTable;
    @FXML TableColumn<Schedulings, Integer> SchedulingID;
    @FXML TableColumn<Schedulings, String> SchedulingCompanyCNPJ;
    @FXML TextField SchedulingPickerTextField;
    @FXML Button SelectSchedulingButton;

    @FXML TextField CompanyCNPJTextField;
    @FXML TextField CompanyNameTextField;
    @FXML TextField CompanyFantasyTextField;

    @FXML TableView<Deliveries> DeliveryViewTable;
    @FXML TableColumn<Deliveries, Integer> DeliveryID;
    @FXML TableColumn<Deliveries, Integer> DeliverySiteID;
    @FXML TableColumn<Deliveries, Timestamp> DeliveryStart;
    @FXML TableColumn<Deliveries, Timestamp> DeliveryEnd;

    @FXML TableView<LicenceDeliverer> DelivererVehicleViewTable;
    @FXML TableColumn<LicenceDeliverer, Integer> DelivererCPF;
    @FXML TableColumn<LicenceDeliverer, String> DelivererName;
    @FXML TableColumn<LicenceDeliverer, Timestamp> VehiclePlate;

    public void GotoMainMenu(ActionEvent actionEvent) {
        Stage stage = (Stage) SchedulingPickerTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainmenu.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Refresh(ActionEvent actionEvent) {
    }

    public void SearchSchedules(ActionEvent actionEvent) {
    }

    public void SelectScheduling(ActionEvent actionEvent) {
        deliveriesList = null;
        licenceDeliverersList = null;

        Schedulings selectedScheduling = SchedulingPickerTable.getSelectionModel().getSelectedItem();
        if(selectedScheduling == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione um agendamento!").show();
            return;
        }

        try {
            Companies company = new SchedulingsDAO().getCompany(selectedScheduling);
            CompanyCNPJTextField.setText(company.getCnpj());
            CompanyNameTextField.setText(company.getName());
            CompanyFantasyTextField.setText(company.getFantasy());
            deliveriesList = new DeliveriesDAO().findByscheduling(selectedScheduling.getId());
            licenceDeliverersList = new LicencesDAO().getDeliverersVehicles(selectedScheduling.getId());
            System.out.println(schedulingsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DeliveryViewTable.setItems(deliveriesList);
        DelivererVehicleViewTable.setItems(licenceDeliverersList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            schedulingsList = new SchedulingsDAO().findAll();
            System.out.println(schedulingsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SchedulingPickerTable.setItems(schedulingsList);
        SchedulingID.setCellValueFactory(new PropertyValueFactory<>("id"));
        SchedulingCompanyCNPJ.setCellValueFactory(new PropertyValueFactory<>("company"));

        DeliveryViewTable.setItems(deliveriesList);
        DeliveryID.setCellValueFactory(new PropertyValueFactory<>("id"));
        DeliverySiteID.setCellValueFactory(new PropertyValueFactory<>("site"));
        DeliveryStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        DeliveryEnd.setCellValueFactory(new PropertyValueFactory<>("end"));

        DelivererVehicleViewTable.setItems(licenceDeliverersList);
        DelivererCPF.setCellValueFactory(new PropertyValueFactory<>("deliverer"));
        DelivererName.setCellValueFactory(new PropertyValueFactory<>("name"));
        VehiclePlate.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
    }
}
