package controller.queries;

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

import database.DeliveriesDAO;
import database.EnumerationsDAO;
import database.PeriodsDAO;
import database.SitesDAO;

import model.insertions.MaterialQuantity;
import model.viewtables.Deliveries;
import model.viewtables.Receivers;
import model.viewtables.Sites;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class QueryDeliveriesDetailedController implements Initializable {
    ObservableList<Deliveries> deliveriesList = null;
    ObservableList<MaterialQuantity> materialQuantityList = null;

    @FXML TableView<Deliveries> DeliveryPickerTable;
    @FXML TableColumn<Deliveries, Integer> DeliveryID;
    @FXML TableColumn<Deliveries, Integer> DeliverySiteID;
    @FXML TableColumn<Deliveries, Integer> DeliverySchedulingID;

    @FXML TableView<MaterialQuantity> MaterialViewTable;
    @FXML TableColumn<MaterialQuantity, Integer> MaterialID;
    @FXML TableColumn<MaterialQuantity, Integer> MaterialDescription;
    @FXML TableColumn<MaterialQuantity, Integer> MaterialWeight;
    @FXML TableColumn<MaterialQuantity, Integer> MaterialDimensions;
    @FXML TableColumn<MaterialQuantity, Integer> MaterialQuantity;

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
        materialQuantityList = null;

        Deliveries selectedDelivery = DeliveryPickerTable.getSelectionModel().getSelectedItem();
        if (selectedDelivery == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione uma Entrega!").show();
            return;
        }

        Sites site = null;
        Receivers receiver = null;
        try {
            site = new SitesDAO().findByid(selectedDelivery.getSite());
            receiver = new PeriodsDAO().getReceiver(selectedDelivery.getSite(), selectedDelivery.getStart(), selectedDelivery.getEnd());
            materialQuantityList = new EnumerationsDAO().getMaterials(selectedDelivery.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SiteIDTextField.setText(site.getId().toString());
        SiteNameTextField.setText(site.getName());
        SiteZipTextField.setText(site.getZip());
        SiteCompanyCNPJTextField.setText(site.getCompany());
        SiteStreetTextField.setText(site.getStreet());
        SiteNumberTextField.setText(site.getNumber().toString());
        SiteCityTextField.setText(site.getCity());
        SiteStateTextField.setText(site.getState());

        ReceiverCPFTextField.setText(receiver.getCpf());
        ReceiverNameTextField.setText(receiver.getName());
        ReceiverRGTextField.setText(receiver.getRg().trim());

        MaterialViewTable.setItems(materialQuantityList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        MaterialViewTable.setItems(materialQuantityList);
        MaterialID.setCellValueFactory(new PropertyValueFactory<>("id"));
        MaterialDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        MaterialWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        MaterialDimensions.setCellValueFactory(new PropertyValueFactory<>("dimension"));
        MaterialQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }
}
