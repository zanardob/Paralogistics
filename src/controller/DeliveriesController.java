package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class DeliveriesController implements Initializable {
    @FXML
    BorderPane mainPane;

    @FXML
    TableView tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void gotoMainMenu(ActionEvent actionEvent) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainmenu.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshTable(ActionEvent actionEvent) {
    }
}
