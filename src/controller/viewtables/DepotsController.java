package controller.viewtables;

import database.DepotsDAO;
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
import model.viewtables.Depots;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class DepotsController implements Initializable{
    @FXML
    TableView<Depots> Table;

    @FXML
    TableColumn<Depots, Integer> SiteID;

    @FXML
    TableColumn<Depots, Integer> Number;

    @FXML
    TableColumn<Depots, String> Capacity;

    @FXML
    TableColumn<Depots, String> Dimensions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Depots> entryList = null;
        try {
            entryList = new DepotsDAO().findAll();
            System.out.println(entryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Table.setItems(entryList);
        SiteID.setCellValueFactory(new PropertyValueFactory<>("site"));
        Number.setCellValueFactory(new PropertyValueFactory<>("number"));
        Capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        Dimensions.setCellValueFactory(new PropertyValueFactory<>("dimensions"));
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
