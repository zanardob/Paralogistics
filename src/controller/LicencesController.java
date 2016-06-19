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
import model.Licences;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class LicencesController implements Initializable{
    @FXML
    TableView<Licences> Table;

    @FXML
    TableColumn<Licences, String> DelivererCPF;

    @FXML
    TableColumn<Licences, Integer> SchedulingID;

    @FXML
    TableColumn<Licences, String> VehiclePlate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Licences> entryList = FXCollections.observableArrayList();

        // Insert code here:
        // Get data from database, create a model object for each entry,
        // and fill the entryList using:
        // entryList.add(model object);

        Table.setItems(entryList);
        DelivererCPF.setCellValueFactory(new PropertyValueFactory<>("deliverer"));
        SchedulingID.setCellValueFactory(new PropertyValueFactory<>("scheduling"));
        VehiclePlate.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
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
