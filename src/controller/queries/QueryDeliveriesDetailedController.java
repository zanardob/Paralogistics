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
import model.viewtables.Deliveries;
import model.viewtables.Receivers;
import model.viewtables.Schedulings;
import model.viewtables.Sites;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 18/06/2016.
 */
public class QueryDeliveriesDetailedController implements Initializable{
    ObservableList<Deliveries> deliveriesList = null;

    // TODO
    // ObeservableList<(PAR DE MATERIALS COM ENUMERATIONS)> materialsEnumerationsList = null;

    @FXML TableView<Deliveries> DeliveryPickerTable;
    @FXML TableColumn<Deliveries, Integer> DeliveryID;
    @FXML TableColumn<Deliveries, Integer> DeliverySiteID;
    @FXML TableColumn<Deliveries, Integer> DeliverySchedulingID;
    @FXML TextField DeliveryPickerTextField;
    @FXML Button SelectDeliveryButton;

    @FXML TextField SiteIDTextField;
    @FXML TextField SiteNameTextField;
    @FXML TextField SiteZipTextField;
    @FXML TextField SiteCompanyCNPJTextField;
    @FXML TextField SiteStreetTextField;
    @FXML TextField SiteNumberTextField;
    @FXML TextField SiteCityTextField;
    @FXML TextField SiteStateTextField;

    @FXML TextField ReceiverCPFTextField;
    @FXML TextField ReceiverNameTextField;
    @FXML TextField ReceiverRGTextField;

    // TODO:
    // DEFINE LICENCES JOIN DELIVERERS TABLE AND TABLECOLUMNS

    public void GotoMainMenu(ActionEvent actionEvent) {
        Stage stage = (Stage) DeliveryPickerTable.getScene().getWindow();
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

    public void SearchDeliveries(ActionEvent actionEvent) {
    }

    public void SelectDelivery(ActionEvent actionEvent) {
        // TODO:
        // materialsEnumerationsList = null;

        Deliveries selectedDelivery = DeliveryPickerTable.getSelectionModel().getSelectedItem();
        if(selectedDelivery == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione uma Entrega!").show();
            return;
        }

        // TODO:
        Sites site = new Sites(0, "oi", "ola", 1, "rip", "dep", "1234", "HUE");
        Receivers receiver = new Receivers("123", "oi", "123");
        // pega os valores certos
        // deliveriesList = DA JOIN DE selectedDelivery COM ENUMERACOES COM MATERIAIS
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deliveriesList = null;

        // TODO:
        // materialsEnumerationsList = null;

        try {
            deliveriesList = new DeliveriesDAO().findAll();
            System.out.println(deliveriesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DeliveryPickerTable.setItems(deliveriesList);
        DeliveryID.setCellValueFactory(new PropertyValueFactory<>("id"));
        DeliverySiteID.setCellValueFactory(new PropertyValueFactory<>("site"));
        DeliverySchedulingID.setCellValueFactory(new PropertyValueFactory<>("scheduling"));

        // TODO:
        // Set MaterialsEnumerations Table and tablecolumns
    }
}
