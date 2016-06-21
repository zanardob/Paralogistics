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

import database.EnumerationsDAO;
import model.viewtables.Enumerations;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EnumerationsController implements Initializable {
    @FXML TableView<Enumerations> Table;
    @FXML TableColumn<Enumerations, Integer> DeliveryID;
    @FXML TableColumn<Enumerations, Integer> MaterialID;
    @FXML TableColumn<Enumerations, Integer> Quantity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Enumerations> entryList = null;
        try {
            entryList = new EnumerationsDAO().findAll();
            System.out.println(entryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Table.setItems(entryList);
        DeliveryID.setCellValueFactory(new PropertyValueFactory<>("delivery"));
        MaterialID.setCellValueFactory(new PropertyValueFactory<>("material"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
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
