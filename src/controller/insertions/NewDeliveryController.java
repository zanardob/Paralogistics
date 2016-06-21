package controller.insertions;

import database.PeriodsDAO;
import database.SitesDAO;
import database.SuppliesDAO;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.insertions.DeliveryEnumerations;
import model.insertions.LicenceDeliverer;
import model.insertions.MaterialQuantity;
import model.viewtables.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class NewDeliveryController implements Initializable {
    ObservableList<Sites> sitesList = null;
    ObservableList<Periods> periodsList = null;
    ObservableList<MaterialQuantity> materialQuantityList = null;
    Sites selectedSite = null;
    Periods selectedPeriod = null;

    @FXML TableView<Sites> SitePickerTable;
    @FXML TableColumn<Sites, String> SiteName;
    @FXML TableColumn<Sites, String> SiteStreet;
    @FXML TableColumn<Sites, Integer> SiteNumber;
    @FXML TableColumn<Sites, String> SiteCity;
    @FXML TableColumn<Sites, String> SiteState;
    @FXML TableColumn<Sites, String> SiteZip;
    @FXML TableColumn<Sites, String> SiteCompanyCNPJ;

    @FXML TableView<Periods> PeriodPickerTable;
    @FXML TableColumn<Periods, Timestamp> SitePeriodStart;

    @FXML TableColumn<Periods, Timestamp> SitePeriodEnd;
    @FXML TableView<model.insertions.MaterialQuantity> MaterialEnumeratorTable;
    @FXML TableColumn<model.insertions.MaterialQuantity, Integer> MaterialID;
    @FXML TableColumn<model.insertions.MaterialQuantity, String> MaterialDescription;
    @FXML TableColumn<model.insertions.MaterialQuantity, String> MaterialWeight;
    @FXML TableColumn<model.insertions.MaterialQuantity, String> MaterialDimensions;
    @FXML TableColumn<MaterialQuantity, Integer> MaterialQuantity;

    @FXML TextField SitePickerTextField;
    @FXML Button SitePickerConfirmButton;
    @FXML TextField MaterialEnumeratorTextField;

    private Companies company = null;
    private ObservableList<DeliveryEnumerations> deliveries;
    private ObservableList<LicenceDeliverer> licences;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            sitesList = new SitesDAO().findAll();
            System.out.println(sitesList);
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
                return site.getName().toLowerCase().contains(siteFilter) || site.getStreet().toLowerCase().contains(siteFilter) || ((site.getCompany() != null) && site.getCompany().contains(siteFilter));
            })
        );

        SitePickerTable.setItems(sortedSites);
        SiteName.setCellValueFactory(new PropertyValueFactory<>("name"));
        SiteStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        SiteNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        SiteCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        SiteState.setCellValueFactory(new PropertyValueFactory<>("state"));
        SiteZip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        SiteCompanyCNPJ.setCellValueFactory(new PropertyValueFactory<>("company"));

        SitePeriodStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        SitePeriodEnd.setCellValueFactory(new PropertyValueFactory<>("end"));

        MaterialID.setCellValueFactory(new PropertyValueFactory<>("id"));
        MaterialDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        MaterialWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        MaterialDimensions.setCellValueFactory(new PropertyValueFactory<>("dimension"));
        MaterialQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        MaterialQuantity.setCellFactory(TextFieldTableCell.<MaterialQuantity, Integer>forTableColumn(new IntegerStringConverter()));
        MaterialQuantity.setOnEditCommit((TableColumn.CellEditEvent<MaterialQuantity, Integer> event) ->
                (event.getTableView().getItems().get(event.getTablePosition().getRow())).setQuantity(event.getNewValue()));

        MaterialEnumeratorTable.setEditable(true);
    }

    private ObservableList<MaterialQuantity> buildList(ObservableList<Materials> materialsList) {
        ObservableList<MaterialQuantity> materialQuantityList = FXCollections.observableArrayList();
        for (Materials material : materialsList) {
            materialQuantityList.add(new MaterialQuantity(material));
        }

        return materialQuantityList;
    }

    public void fillMaterialsTable() {
        try {
            ObservableList<Materials> materialsList = new SuppliesDAO().getMaterials(company.getCnpj());
            materialQuantityList = buildList(materialsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FilteredList<MaterialQuantity> filteredMaterialAndQuantity = new FilteredList<>(materialQuantityList, m -> true);
        SortedList<MaterialQuantity> sortedMaterialAndQuantity = new SortedList<>(filteredMaterialAndQuantity);
        sortedMaterialAndQuantity.comparatorProperty().bind(MaterialEnumeratorTable.comparatorProperty());

        MaterialEnumeratorTextField.textProperty().addListener((observable, oldValue, newValue) ->
            filteredMaterialAndQuantity.setPredicate(material -> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String materialFilter = newValue.toLowerCase();
                return material.getId().equals(Integer.parseInt(materialFilter)) || material.getDescription().toLowerCase().contains(materialFilter);
            })
        );

        MaterialEnumeratorTable.setItems(materialQuantityList);
        System.out.println(materialQuantityList);
        System.out.println("Deveria ter impresso a lista de materiais da empresa de cnpj: " + company.getCnpj());
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public void setDeliveries(ObservableList<DeliveryEnumerations> deliveries) {
        this.deliveries = deliveries;
    }

    public void setLicences(ObservableList<LicenceDeliverer> licences) {
        this.licences = licences;
    }

    public void Cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) SitePickerTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/insertions/newscheduling2.fxml"));
            Parent root = loader.load();
            NewScheduling2Controller controller = loader.getController();
            controller.setCompany(company);
            controller.setDeliveries(deliveries);
            controller.setLicences(licences);
            controller.refreshTables();
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

        if (selectedSite == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione um Local").show();
            return;
        }

        try {
            periodsList = new PeriodsDAO().findBysite(selectedSite.getId());
            PeriodPickerTable.setItems(periodsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(periodsList);
        System.out.println("Deveria ter impresso a lista de períodos do local de Nome: " + selectedSite.getName());
    }

    public void SearchMaterials(ActionEvent actionEvent) {

    }

    public void Confirm(ActionEvent actionEvent) {
        selectedPeriod = null;
        selectedPeriod = PeriodPickerTable.getSelectionModel().getSelectedItem();
        if (selectedPeriod == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione um Período").show();
            return;
        }

        DeliveryEnumerations delivery = new DeliveryEnumerations(0, selectedSite.getId(), selectedPeriod.getStart(), selectedPeriod.getEnd(), 0);
        for (model.insertions.MaterialQuantity material : materialQuantityList) {
            if (material.getQuantity() > 0) {
                delivery.addEnumeration(new Enumerations(delivery.getId(), material.getId(), material.getQuantity()));
            }
        }
        if (!delivery.hasEnumerations()) {
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
            controller.refreshTables();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
