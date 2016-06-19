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
import model.Vehicles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class VehiclesController implements Initializable{
    @FXML
    TableView<Vehicles> Table;

    @FXML
    TableColumn<Vehicles, Integer> Plate;

    @FXML
    TableColumn<Vehicles, java.sql.Date> ConcessionStart;

    @FXML
    TableColumn<Vehicles, java.sql.Date> ConcessionEnd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Vehicles> entryList = FXCollections.observableArrayList();

        // Insert code here:
        // Get data from database, create a model object for each entry,
        // and fill the entryList using:
        // entryList.add(model object);

        Table.setItems(entryList);
        Plate.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        ConcessionStart.setCellValueFactory(new PropertyValueFactory<>("concession_start"));
        ConcessionEnd.setCellValueFactory(new PropertyValueFactory<>("concession_end"));
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