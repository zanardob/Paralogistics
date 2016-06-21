package controller.queries;

import database.DeliveriesDAO;
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

    // TODO
    // ObeservableList<(PAR DE DELIVERERS COM LICENCES)> deliverersVehiclesList = null;

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

    // TODO:
    // DEFINE LICENCES JOIN DELIVERERS TABLE AND TABLECOLUMNS

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
        // TODO:
        // deliverersVehiclesList = null;

        Schedulings selectedScheduling = SchedulingPickerTable.getSelectionModel().getSelectedItem();
        if(selectedScheduling == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione um agendamento!").show();
            return;
        }

        // TODO:
        // deliveriesList = DA JOIN DE selectedScheduling COM ENTREGAS
        // deliverersVehiclesList = DA JOIN DE selectedScheduling COM LICENCES COM DELIVERERS
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        schedulingsList = null;
        deliveriesList = null;

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

        // TODO:
        // Set DeliverersVehicles Table and tablecolumns
    }
}
