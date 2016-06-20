package controller.insertions;

import database.SitesDAO;
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
import model.entities.Companies;
import model.entities.Enumerations;
import model.entities.Periods;
import model.entities.Sites;
import model.special.DeliveryAndEnumerations;
import model.special.LicencedDeliverer;
import model.special.MaterialAndQuantity;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class NewDeliveryController implements Initializable{
    private Companies company = null;
    private ObservableList<DeliveryAndEnumerations> deliveries;
    private ObservableList<LicencedDeliverer> licences;

    ObservableList<Sites> sitesList;
    ObservableList<Periods> periodsList;
    ObservableList<MaterialAndQuantity> materialsList;

    Sites selectedSite;
    Periods selectedPeriod;

    @FXML TableView<Sites> SitePickerTable;
    @FXML TableColumn<Sites, String> SiteName;
    @FXML TableColumn<Sites, String> SiteStreet;
    @FXML TableColumn<Sites, Integer> SiteNumber;
    @FXML TableColumn<Sites, String> SiteCity;
    @FXML TableColumn<Sites, String> SiteState;
    @FXML TableColumn<Sites, String> SiteZip;
    @FXML TableColumn<Sites, String> SiteCompanyCNPJ;
    @FXML TextField SitePickerTextField;
    @FXML Button SitePickerConfirmButton;

    @FXML TableView<Periods> PeriodPickerTable;
    @FXML TableColumn<Periods, java.sql.Date> SitePeriodStart;
    @FXML TableColumn<Periods, java.sql.Date> SitePeriodEnd;

    @FXML TableView<MaterialAndQuantity> MaterialEnumeratorTable;
    @FXML TableColumn<MaterialAndQuantity, Integer> MaterialID;
    @FXML TableColumn<MaterialAndQuantity, String> MaterialDescription;
    @FXML TableColumn<MaterialAndQuantity, String> MaterialWeight;
    @FXML TableColumn<MaterialAndQuantity, String> MaterialDimensions;
    @FXML TableColumn<MaterialAndQuantity, Integer> MaterialQuantity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sitesList = null;
        periodsList = null;
        materialsList = null;
        try {
            sitesList = new SitesDAO().findAll();
            System.out.println(sitesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SitePickerTable.setItems(sitesList);
        SiteName.setCellValueFactory(new PropertyValueFactory<>("name"));
        SiteStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        SiteNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        SiteCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        SiteState.setCellValueFactory(new PropertyValueFactory<>("state"));
        SiteZip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        SiteCompanyCNPJ.setCellValueFactory(new PropertyValueFactory<>("company"));

        PeriodPickerTable.setItems(periodsList);
        SitePeriodStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        SitePeriodEnd.setCellValueFactory(new PropertyValueFactory<>("end"));

        MaterialEnumeratorTable.setItems(materialsList);
        MaterialID.setCellValueFactory(new PropertyValueFactory<>("id"));
        MaterialDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        MaterialWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        MaterialDimensions.setCellValueFactory(new PropertyValueFactory<>("dimensions"));
        MaterialQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        MaterialQuantity.setEditable(true);
    }

    public void fillMaterialsTable() {
        // materialsList = PEGA OS MATERIAL DA EMPRESA (deliveringCompany)
        System.out.println(materialsList);
        System.out.println("Deveria ter impresso a lista de materiais da empresa de cnpj: " + company.getCnpj());
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public void setDeliveries(ObservableList<DeliveryAndEnumerations> deliveries) {
        this.deliveries = deliveries;
    }

    public void setLicences(ObservableList<LicencedDeliverer> licences) {
        this.licences = licences;
    }

    public void Cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) SitePickerTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/insertions/newscheduling.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SearchSites(ActionEvent actionEvent) {
    }

    public void ConfirmSite(ActionEvent actionEvent) {
        periodsList = null;
        selectedPeriod = null;
        selectedSite = null;
        selectedSite = SitePickerTable.getSelectionModel().getSelectedItem();
        if(selectedSite == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione um Local").show();
            return;
        }
        // Insert a query here to fill the list
        // periodsList = (GET ALL PERIODS FROM selectedSite)
        System.out.println(periodsList);
        System.out.println("Deveria ter impresso a lista de períodos do local de Nome: " + selectedSite.getName());
    }

    public void SearchMaterials(ActionEvent actionEvent) {

    }

    public void Confirm(ActionEvent actionEvent) {
        selectedPeriod = null;
        selectedPeriod = PeriodPickerTable.getSelectionModel().getSelectedItem();
        if(selectedPeriod == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione um Período").show();
            return;
        }

        DeliveryAndEnumerations delivery = new DeliveryAndEnumerations(0, selectedSite.getId(), selectedPeriod.getStart(), selectedPeriod.getEnd(), 0);
        for(MaterialAndQuantity material : materialsList) {
            if (material.getQuantity() > 0) {
                delivery.addEnumeration(new Enumerations(delivery.getId(), material.getId(), material.getQuantity()));
                break;
            }
        }
        if(!delivery.hasEnumerations()) {
            new Alert(Alert.AlertType.ERROR, "Adicione materiais à entrega!").show();
            return;
        }

        Stage stage = (Stage) SitePickerTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/insertions/newscheduling2.fxml"));
            Parent root = loader.load();
            deliveries.add(delivery);
            NewScheduling2Controller controller = loader.getController();
            controller.setCompany(company);
            controller.setDeliveries(deliveries);
            controller.setLicences(licences);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
