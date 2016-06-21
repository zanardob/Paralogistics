package controller.queries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;

import database.CompaniesDAO;
import model.viewtables.Companies;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class QueryStatsController implements Initializable {
    Companies mostDeliveriesCompany = null;
    Companies heaviestCompany = null;

    @FXML TextField MostDeliveriesCompanyCNPJTextField;
    @FXML TextField MostDeliveriesCompanyNameTextField;
    @FXML TextField MostDeliveriesCompanyFantasyTextField;
    @FXML TextField MostDeliveriesQuantity;

    @FXML TextField HeaviestCompanyCNPJTextField;
    @FXML TextField HeaviestCompanyNameTextField;
    @FXML TextField HeaviestCompanyFantasyTextField;
    @FXML TextField HeaviestCompanyWeight;

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
        Pair<Companies, Integer> mostDeliveriesPair = null;
        Pair<Companies, Integer> heaviestCompanyPair = null;

        try {
            mostDeliveriesPair = new CompaniesDAO().getMostDeliveries();
            heaviestCompanyPair = new CompaniesDAO().getHeaviest();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mostDeliveriesCompany = mostDeliveriesPair.getKey();
        heaviestCompany = heaviestCompanyPair.getKey();

        Integer numDeliveries = mostDeliveriesPair.getValue();
        Integer totalWeight = heaviestCompanyPair.getValue();

        MostDeliveriesCompanyCNPJTextField.setText(mostDeliveriesCompany.getCnpj());
        MostDeliveriesCompanyNameTextField.setText(mostDeliveriesCompany.getName());
        MostDeliveriesCompanyFantasyTextField.setText(mostDeliveriesCompany.getFantasy());
        MostDeliveriesQuantity.setText(numDeliveries.toString());

        HeaviestCompanyCNPJTextField.setText(heaviestCompany.getCnpj());
        HeaviestCompanyNameTextField.setText(heaviestCompany.getName());
        HeaviestCompanyFantasyTextField.setText(heaviestCompany.getFantasy());
        HeaviestCompanyWeight.setText(totalWeight.toString());
    }
}
