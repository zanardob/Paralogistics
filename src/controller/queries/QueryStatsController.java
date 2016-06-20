package controller.queries;

import database.DeliveriesDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.viewtables.Deliveries;
import model.viewtables.Receivers;
import model.viewtables.Sites;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 18/06/2016.
 */
public class QueryStatsController implements Initializable{
    @FXML TextField MostDeliveriesCompanyCNPJTextField;
    @FXML TextField MostDeliveriesCompanyNameTextField;
    @FXML TextField MostDeliveriesCompanyFantasyTextField;
    @FXML TextField MostDeliveriesQuantity;

    @FXML TextField HeaviestCompanyCNPJTextField;
    @FXML TextField HeaviestCompanyNameTextField;
    @FXML TextField HeaviestCompanyFantasyTextField;
    @FXML TextField HeaviestCompanyWeight;

    // TODO:
    // DEFINE LICENCES JOIN DELIVERERS TABLE AND TABLECOLUMNS

    public void GotoMainMenu(ActionEvent actionEvent) {
        Stage stage = (Stage) MostDeliveriesCompanyCNPJTextField.getScene().getWindow();
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
        // TODO
        // ALL THE SHIT!
    }
}
