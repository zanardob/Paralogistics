package controller.queries;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import database.CompaniesDAO;
import database.DepotsDAO;
import database.SitesDAO;
import database.SuppliesDAO;

import model.viewtables.Companies;
import model.viewtables.Depots;
import model.viewtables.Materials;
import model.viewtables.Sites;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class QueryCompaniesController implements Initializable {
    ObservableList<Companies> companiesList = null;
    ObservableList<Sites> sitesList = null;
    ObservableList<Materials> materialsList = null;
    ObservableList<Depots> depotsList = null;

    @FXML TableView<Companies> CompanyPickerTable;
    @FXML TableColumn<Companies, String> CompanyCNPJ;
    @FXML TableColumn<Companies, String> CompanyName;
    @FXML TableColumn<Companies, String> CompanyFantasy;

    @FXML TableView<Sites> SitePickerTable;
    @FXML TableColumn<Sites, Integer> SiteID;
    @FXML TableColumn<Sites, String> SiteName;
    @FXML TableColumn<Sites, String> SiteStreet;
    @FXML TableColumn<Sites, Integer> SiteNumber;
    @FXML TableColumn<Sites, String> SiteCity;
    @FXML TableColumn<Sites, String> SiteState;

    @FXML TableView<Materials> MaterialViewTable;
    @FXML TableColumn<Materials, Integer> MaterialID;
    @FXML TableColumn<Materials, String> MaterialDescription;
    @FXML TableColumn<Materials, String> MaterialWeight;
    @FXML TableColumn<Materials, String> MaterialDimensions;

    @FXML TableView<Depots> DepotViewTable;
    @FXML TableColumn<Depots, Integer> DepotNumber;
    @FXML TableColumn<Depots, String> DepotCapacity;
    @FXML TableColumn<Depots, String> DepotDimensions;

    @FXML TextField CompanyPickerTextField;
    @FXML Button SelectCompanyButton;
    @FXML TextField SitePickerTextField;
    @FXML Button SelectSiteButton;

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
        if (selectedCompany == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione uma Empresa!").show();
            return;
        }

        try {
            sitesList = new SitesDAO().findBycompany(selectedCompany.getCnpj());
            materialsList = new SuppliesDAO().getMaterials(selectedCompany.getCnpj());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FilteredList<Sites> filteredSites = new FilteredList<>(sitesList, s -> true);
        SortedList<Sites> sortedSites = new SortedList<>(filteredSites);
        sortedSites.comparatorProperty().bind(SitePickerTable.comparatorProperty());

        SitePickerTextField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredSites.setPredicate(site -> {
                    if (newValue == null || newValue.isEmpty())
                        return true;

                    String siteFilter = newValue.toLowerCase();
                    return site.getId().toString().contains(siteFilter) || site.getName().toLowerCase().contains(siteFilter) || site.getStreet().toLowerCase().contains(siteFilter) || site.getCity().toLowerCase().contains(siteFilter) || site.getState().toLowerCase().contains(siteFilter) || ((site.getCompany() != null) && site.getCompany().contains(siteFilter));
                })
        );

        SitePickerTable.setItems(sortedSites);
        MaterialViewTable.setItems(materialsList);
    }

    public void SearchSites(ActionEvent actionEvent) {
    }

    public void SelectSite(ActionEvent actionEvent) {
        depotsList = null;

        Sites selectedSite = SitePickerTable.getSelectionModel().getSelectedItem();
        if (selectedSite == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione um local!").show();
            return;
        }

        try {
            depotsList = new DepotsDAO().findBysite(selectedSite.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DepotViewTable.setItems(depotsList);
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

        FilteredList<Companies> filteredCompanies = new FilteredList<>(companiesList, c -> true);
        SortedList<Companies> sortedCompanies = new SortedList<>(filteredCompanies);
        sortedCompanies.comparatorProperty().bind(CompanyPickerTable.comparatorProperty());

        CompanyPickerTextField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredCompanies.setPredicate(company -> {
                    if (newValue == null || newValue.isEmpty())
                        return true;

                    String companyFilter = newValue.toLowerCase();
                    return company.getCnpj().contains(companyFilter) || company.getName().toLowerCase().contains(companyFilter) || company.getFantasy().toLowerCase().contains(companyFilter);
                })
        );
        
        CompanyPickerTable.setItems(sortedCompanies);
        CompanyCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        CompanyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        CompanyFantasy.setCellValueFactory(new PropertyValueFactory<>("fantasy"));

        SiteID.setCellValueFactory(new PropertyValueFactory<>("id"));
        SiteName.setCellValueFactory(new PropertyValueFactory<>("name"));
        SiteStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        SiteNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        SiteCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        SiteState.setCellValueFactory(new PropertyValueFactory<>("state"));

        MaterialID.setCellValueFactory(new PropertyValueFactory<>("id"));
        MaterialDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        MaterialWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        MaterialDimensions.setCellValueFactory(new PropertyValueFactory<>("dimension"));

        DepotNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        DepotCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        DepotDimensions.setCellValueFactory(new PropertyValueFactory<>("dimension"));
    }
}
