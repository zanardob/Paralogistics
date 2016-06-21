package controller.queries;

import database.DeliveriesDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.viewtables.Deliveries;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class QueryDeliveriesController implements Initializable {

    @FXML TableView<Deliveries> AlreadyDeliveredTable;
    @FXML TableColumn<Deliveries, Integer> AlreadyDeliveredID;
    @FXML TableColumn<Deliveries, Integer> AlreadyDeliveredSiteID;
    @FXML TableColumn<Deliveries, Timestamp> AlreadyDeliveredStart;
    @FXML TableColumn<Deliveries, Timestamp> AlreadyDeliveredEnd;
    @FXML TableColumn<Deliveries, Integer> AlreadyDeliveredSchedulingID;
    @FXML TextField AlreadyDeliveredTextField;

    @FXML TableView<Deliveries> ToBeDeliveredTable;
    @FXML TableColumn<Deliveries, Integer> ToBeDeliveredID;
    @FXML TableColumn<Deliveries, Integer> ToBeDeliveredSiteID;
    @FXML TableColumn<Deliveries, Timestamp> ToBeDeliveredStart;
    @FXML TableColumn<Deliveries, Timestamp> ToBeDeliveredEnd;
    @FXML TableColumn<Deliveries, Integer> ToBeDeliveredSchedulingID;
    @FXML TextField ToBeDeliveredTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Deliveries> alreadyDeliveredList = null;
        ObservableList<Deliveries> toBeDeliveredList = null;

        try {
            alreadyDeliveredList = new DeliveriesDAO().getFromDate("<");
            toBeDeliveredList = new DeliveriesDAO().getFromDate(">=");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(alreadyDeliveredList);
        System.out.println("deveria ter preenchido a lista de entregas ja feitas");
        System.out.println(toBeDeliveredList);
        System.out.println("deveria ter preenchido a lista de entregas ainda nao feitas");

        AlreadyDeliveredTable.setItems(alreadyDeliveredList);
        AlreadyDeliveredID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AlreadyDeliveredSiteID.setCellValueFactory(new PropertyValueFactory<>("site"));
        AlreadyDeliveredStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        AlreadyDeliveredEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        AlreadyDeliveredSchedulingID.setCellValueFactory(new PropertyValueFactory<>("scheduling"));

        ToBeDeliveredTable.setItems(toBeDeliveredList);
        ToBeDeliveredID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ToBeDeliveredSiteID.setCellValueFactory(new PropertyValueFactory<>("site"));
        ToBeDeliveredStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        ToBeDeliveredEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        ToBeDeliveredSchedulingID.setCellValueFactory(new PropertyValueFactory<>("scheduling"));
    }

    public void GotoMainMenu(ActionEvent actionEvent) {
        Stage stage = (Stage) AlreadyDeliveredTable.getScene().getWindow();
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

    public void SearchAlreadyDelivered(ActionEvent actionEvent) {
    }

    public void SearchToBeDelivered(ActionEvent actionEvent) {
    }
}
