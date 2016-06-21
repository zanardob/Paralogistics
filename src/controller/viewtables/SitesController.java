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

import database.SitesDAO;
import model.viewtables.Sites;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SitesController implements Initializable {
    @FXML TableView<Sites> Table;
    @FXML TableColumn<Sites, Integer> ID;
    @FXML TableColumn<Sites, String> Name;
    @FXML TableColumn<Sites, String> Street;
    @FXML TableColumn<Sites, Integer> Number;
    @FXML TableColumn<Sites, String> City;
    @FXML TableColumn<Sites, String> State;
    @FXML TableColumn<Sites, String> Zip;
    @FXML TableColumn<Sites, String> CompanyCNPJ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Sites> entryList = null;
        try {
            entryList = new SitesDAO().findAll();
            System.out.println(entryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Table.setItems(entryList);
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Street.setCellValueFactory(new PropertyValueFactory<>("street"));
        Number.setCellValueFactory(new PropertyValueFactory<>("number"));
        City.setCellValueFactory(new PropertyValueFactory<>("city"));
        State.setCellValueFactory(new PropertyValueFactory<>("state"));
        Zip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        CompanyCNPJ.setCellValueFactory(new PropertyValueFactory<>("company"));
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
