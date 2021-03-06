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

import database.SchedulingsDAO;
import model.viewtables.Schedulings;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SchedulingsController implements Initializable {
    @FXML TableView<Schedulings> Table;
    @FXML TableColumn<Schedulings, Integer> ID;
    @FXML TableColumn<Schedulings, String> CompanyCNPJ;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Schedulings> entryList = null;
        try {
            entryList = new SchedulingsDAO().findAll();
            System.out.println(entryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Table.setItems(entryList);
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        CompanyCNPJ.setCellValueFactory(new PropertyValueFactory<>("company"));
    }
}
