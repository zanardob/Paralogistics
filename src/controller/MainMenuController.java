package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 18/06/2016.
 */
public class MainMenuController implements Initializable {
    @FXML
    BorderPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void showScheduling(ActionEvent actionEvent) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/schedulings.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDepots(ActionEvent actionEvent) {
    }

    public void showEnumerations(ActionEvent actionEvent) {
    }

    public void showCompanies(ActionEvent actionEvent) {
    }

    public void showDeliveries(ActionEvent actionEvent) {
    }

    public void showDeliverers(ActionEvent actionEvent) {
    }

    public void showSuplies(ActionEvent actionEvent) {
    }

    public void showLicenses(ActionEvent actionEvent) {
    }

    public void showSites(ActionEvent actionEvent) {
    }

    public void showMaterials(ActionEvent actionEvent) {
    }

    public void showPeriods(ActionEvent actionEvent) {
    }

    public void showReceivers(ActionEvent actionEvent) {
    }

    public void showVehicles(ActionEvent actionEvent) {
    }

    public void newScheduling(ActionEvent actionEvent) {
    }
}
