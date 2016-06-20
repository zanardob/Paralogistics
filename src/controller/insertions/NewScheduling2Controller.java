package controller.insertions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.entities.Companies;
import model.entities.Deliveries;
import model.entities.Enumerations;
import model.special.DeliveryAndEnumerations;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class NewScheduling2Controller implements Initializable {
    @FXML
    TableView AddLicenceTable;

    private Companies company = null;
    private ObservableList<DeliveryAndEnumerations> deliveries;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deliveries = FXCollections.observableArrayList();
    }

    public void AddLicence(ActionEvent actionEvent) {
    }

    public void AddDelivery(ActionEvent actionEvent) {
        Stage stage = (Stage) AddLicenceTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/insertions/newdelivery.fxml"));
            Parent root = loader.load();
            NewDeliveryController controller = loader.getController();
            controller.setDeliveringCompany(company);
            controller.fillMaterialsTable();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDeliveryToList(DeliveryAndEnumerations delivery) {
        deliveries.add(delivery);
    }

    public void Cancel(ActionEvent actionEvent) {
        deliveries.clear();
        Stage stage = (Stage) AddLicenceTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainmenu.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Confirm(ActionEvent actionEvent) {

    }

    public void setCompany(Companies cpn) {
        this.company = cpn;
    }
}
