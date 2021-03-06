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

import database.DeliveriesDAO;
import database.SitesDAO;

import model.viewtables.Deliveries;
import model.viewtables.Sites;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class QueryPeriodsController implements Initializable {
    ObservableList<Sites> sitesList = null;
    ObservableList<Deliveries> deliveriesList = null;

    @FXML TableView<Sites> SitePickerTable;
    @FXML TableColumn<Sites, Integer> SiteID;
    @FXML TableColumn<Sites, String> SiteName;
    @FXML TableColumn<Sites, String> SiteStreet;
    @FXML TableColumn<Sites, Integer> SiteNumber;
    @FXML TableColumn<Sites, String> SiteCity;
    @FXML TableColumn<Sites, String> SiteState;
    @FXML TableColumn<Sites, String> SiteZip;
    @FXML TableColumn<Sites, String> SiteCompanyCNPJ;

    @FXML TableView<Deliveries> DeliveryViewTable;
    @FXML TableColumn<Deliveries, Integer> DeliveryID;
    @FXML TableColumn<Deliveries, String> DeliverySchedulingID;
    @FXML TableColumn<Deliveries, String> DeliveryCompanyCNPJ;
    @FXML TableColumn<Deliveries, String> DeliveredMaterials;

    @FXML TextField SitePickerTextField;
    @FXML DatePicker PeriodDatePicker;
    @FXML Button SelectSiteAndPeriodButton;

    public void GotoMainMenu(ActionEvent actionEvent) {
        Stage stage = (Stage) SitePickerTable.getScene().getWindow();
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

    public void SelectSiteAndPeriod(ActionEvent actionEvent) {
        sitesList = null;
        deliveriesList = null;
        LocalDate date;

        Sites selectedSite = SitePickerTable.getSelectionModel().getSelectedItem();
        date = PeriodDatePicker.getValue();
        if (date == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione um Local E Data!").show();
            return;
        }
        Timestamp selectedDate = Timestamp.valueOf(date.atStartOfDay());
        if ((selectedSite == null) || (selectedDate == null)) {
            new Alert(Alert.AlertType.ERROR, "Selecione um Local E Data!").show();
            return;
        }
        System.out.println(selectedDate);
        System.out.println(selectedSite.getName());

        try {
            deliveriesList = new DeliveriesDAO().findBySiteAndEnd(selectedSite.getId(), selectedDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DeliveryViewTable.setItems(deliveriesList);
    }

    public void SearchSites(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sitesList = null;
        deliveriesList = null;

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
                return site.getId().toString().contains(siteFilter) || site.getName().toLowerCase().contains(siteFilter) || site.getStreet().toLowerCase().contains(siteFilter) || site.getCity().toLowerCase().contains(siteFilter) || site.getState().toLowerCase().contains(siteFilter) || ((site.getCompany() != null) && site.getCompany().contains(siteFilter));
            })
        );

        SitePickerTable.setItems(sortedSites);
        SiteID.setCellValueFactory(new PropertyValueFactory<>("id"));
        SiteName.setCellValueFactory(new PropertyValueFactory<>("name"));
        SiteStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        SiteNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        SiteCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        SiteState.setCellValueFactory(new PropertyValueFactory<>("state"));
        SiteZip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        SiteCompanyCNPJ.setCellValueFactory(new PropertyValueFactory<>("company"));

        DeliveryID.setCellValueFactory(new PropertyValueFactory<>("id"));
        DeliverySchedulingID.setCellValueFactory(new PropertyValueFactory<>("scheduling"));
    }
}
