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
import model.Sites;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class SitesController implements Initializable{
    @FXML
    TableView<Sites> Table;

    @FXML
    TableColumn<Sites, Integer> ID;

    @FXML
    TableColumn<Sites, String> Name;

    @FXML
    TableColumn<Sites, String> Street;

    @FXML
    TableColumn<Sites, Integer> Number;

    @FXML
    TableColumn<Sites, String> City;

    @FXML
    TableColumn<Sites, String> State;

    @FXML
    TableColumn<Sites, String> Zip;

    @FXML
    TableColumn<Sites, String> CompanyCNPJ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Sites> entryList = FXCollections.observableArrayList();

        // Insert code here:
        // Get data from database, create a model object for each entry,
        // and fill the entryList using:
        // entryList.add(model object);

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
