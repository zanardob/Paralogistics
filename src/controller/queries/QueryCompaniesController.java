package controller.queries;

import database.CompaniesDAO;
import database.SchedulingsDAO;
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
import model.viewtables.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 18/06/2016.
 */
public class QueryCompaniesController implements Initializable{
    ObservableList<Companies> companiesList = null;
    ObservableList<Sites> sitesList = null;
    ObservableList<Materials> materialsList = null;
    ObservableList<Depots> depotsList = null;

    @FXML TableView<Companies> CompanyPickerTable;
    @FXML TableColumn<Companies, String> CompanyCNPJ;
    @FXML TableColumn<Companies, String> CompanyName;
    @FXML TableColumn<Companies, String> CompanyFantasy;
    @FXML TextField CompanyPickerTextField;
    @FXML Button SelectCompanyButton;

    @FXML TableView<Sites> SitePickerTable;
    @FXML TableColumn<Sites, Integer> SiteID;
    @FXML TableColumn<Sites, String> SiteName;
    @FXML TableColumn<Sites, String> SiteStreet;
    @FXML TableColumn<Sites, Integer> SiteNumber;
    @FXML TableColumn<Sites, String> SiteCity;
    @FXML TableColumn<Sites, String> SiteState;
    @FXML TextField SitePickerTextField;
    @FXML Button SelectSiteButton;

    @FXML TableView<Materials> MaterialViewTable;
    @FXML TableColumn<Materials, Integer> MaterialID;
    @FXML TableColumn<Materials, String> MaterialDescription;
    @FXML TableColumn<Materials, String> MaterialWeight;
    @FXML TableColumn<Materials, String> MaterialDimensions;

    @FXML TableView<Depots> DepotViewTable;
    @FXML TableColumn<Depots, Integer> DepotNumber;
    @FXML TableColumn<Depots, String> DepotCapacity;
    @FXML TableColumn<Depots, String> DepotDimensions;

    // TODO:
    // DEFINE LICENCES JOIN DELIVERERS TABLE AND TABLECOLUMNS

    public void GotoMainMenu(ActionEvent actionEvent) {
        Stage stage = (Stage) CompanyPickerTable.getScene().getWindow();
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

    public void SearchCompanies(ActionEvent actionEvent) {
    }

    public void SelectCompany(ActionEvent actionEvent) {
        sitesList = null;
        materialsList = null;
        depotsList = null;

        Companies selectedCompany = CompanyPickerTable.getSelectionModel().getSelectedItem();
        if(selectedCompany == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione uma Empresa!").show();
            return;
        }

        // TODO:
        // sitesList = DA JOIN DE selectedCompany COM LOCIAS
        // materialsList = DA JOIN DE selectedCompany COM MATERIAIS
    }

    public void SearchSites(ActionEvent actionEvent) {
    }

    public void SelectSite(ActionEvent actionEvent) {
        depotsList = null;

        Sites selectedSite = SitePickerTable.getSelectionModel().getSelectedItem();
        if(selectedSite == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione um local!").show();
            return;
        }

        // TODO:
        // depotsList = DA JOIN DE selectedSite COM DEPOSITOS
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        companiesList = null;
        sitesList = null;
        materialsList = null;
        depotsList = null;

        try {
            companiesList = new CompaniesDAO().findAll();
            System.out.println(companiesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CompanyPickerTable.setItems(companiesList);
        CompanyCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        CompanyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        CompanyFantasy.setCellValueFactory(new PropertyValueFactory<>("fantasy"));

        SitePickerTable.setItems(sitesList);
        SiteID.setCellValueFactory(new PropertyValueFactory<>("id"));
        SiteName.setCellValueFactory(new PropertyValueFactory<>("name"));
        SiteStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        SiteNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        SiteCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        SiteState.setCellValueFactory(new PropertyValueFactory<>("state"));

        MaterialViewTable.setItems(materialsList);
        MaterialID.setCellValueFactory(new PropertyValueFactory<>("id"));
        MaterialDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        MaterialWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        MaterialDimensions.setCellValueFactory(new PropertyValueFactory<>("dimension"));

        DepotViewTable.setItems(depotsList);
        DepotNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        DepotCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        DepotDimensions.setCellValueFactory(new PropertyValueFactory<>("dimension"));
    }
}