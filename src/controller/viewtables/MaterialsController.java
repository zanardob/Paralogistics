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

import database.MaterialsDAO;
import model.viewtables.Materials;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MaterialsController implements Initializable {
    @FXML TableView<Materials> Table;
    @FXML TableColumn<Materials, Integer> ID;
    @FXML TableColumn<Materials, String> Description;
    @FXML TableColumn<Materials, String> Weight;
    @FXML TableColumn<Materials, String> Dimensions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Materials> entryList = null;
        try {
            entryList = new MaterialsDAO().findAll();
            System.out.println(entryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Table.setItems(entryList);
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        Dimensions.setCellValueFactory(new PropertyValueFactory<>("dimension"));
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
