package controller.viewtables;

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
import javafx.stage.Stage;

import database.VehiclesDAO;
import model.viewtables.Vehicles;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class VehiclesController implements Initializable {
    @FXML TableView<Vehicles> Table;
    @FXML TableColumn<Vehicles, Integer> Plate;
    @FXML TableColumn<Vehicles, Timestamp> ConcessionStart;
    @FXML TableColumn<Vehicles, Timestamp> ConcessionEnd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Vehicles> entryList = null;
        try {
            entryList = new VehiclesDAO().findAll();
            System.out.println(entryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Table.setItems(entryList);
        Plate.setCellValueFactory(new PropertyValueFactory<>("plate"));
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
