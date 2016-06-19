package controller;

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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Deliveries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class DeliveriesController implements Initializable {
    @FXML
    TableView<Deliveries> Table;

    @FXML
    TableColumn<Deliveries, Integer> ID;

    @FXML
    TableColumn<Deliveries, Integer> SiteID;

    @FXML
    TableColumn<Deliveries, java.sql.Date> DeliveryStart;

    @FXML
    TableColumn<Deliveries, java.sql.Date> DeliveryEnd;

    @FXML
    TableColumn<Deliveries, Integer> SchedulingID;

    @FXML
    TableColumn<Deliveries, String> ReceiverCPF;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Deliveries> entryList = FXCollections.observableArrayList();

        // Insert code here:
        // Get data from database, create a model object for each entry,
        // and fill the entryList using:
        // entryList.add(model object);

        Table.setItems(entryList);
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        SiteID.setCellValueFactory(new PropertyValueFactory<>("site"));
        DeliveryStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        DeliveryEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        SchedulingID.setCellValueFactory(new PropertyValueFactory<>("scheduling"));
        ReceiverCPF.setCellValueFactory(new PropertyValueFactory<>("receiver"));
    }

    public void GotoMainMenu(ActionEvent actionEvent) {
        Stage stage = (Stage) Table.getScene().getWindow();
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
}
