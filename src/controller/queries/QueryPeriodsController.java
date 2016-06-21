package controller.queries;

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
import model.queries.DeliverySchedulingEnumerations;
import model.viewtables.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 18/06/2016.
 */
public class QueryPeriodsController implements Initializable{
    ObservableList<Sites> sitesList = null;
    ObservableList<DeliverySchedulingEnumerations> deliveriesList = null;

    @FXML TableView<Sites> SitePickerTable;
    @FXML TableColumn<Sites, Integer> SiteID;
    @FXML TableColumn<Sites, String> SiteName;
    @FXML TableColumn<Sites, String> SiteStreet;
    @FXML TableColumn<Sites, Integer> SiteNumber;
    @FXML TableColumn<Sites, String> SiteCity;
    @FXML TableColumn<Sites, String> SiteState;
    @FXML TableColumn<Sites, String> SiteZip;
    @FXML TableColumn<Sites, String> SiteCompanyCNPJ;
    @FXML TextField SitePickerTextField;
    @FXML DatePicker PeriodDatePicker;
    @FXML Button SelectSiteAndPeriodButton;

    @FXML TableView<DeliverySchedulingEnumerations> DeliveryViewTable;
    @FXML TableColumn<DeliverySchedulingEnumerations, Integer> DeliveryID;
    @FXML TableColumn<DeliverySchedulingEnumerations, String> DeliverySchedulingID;
    @FXML TableColumn<DeliverySchedulingEnumerations, String> DeliveryCompanyCNPJ;
    @FXML TableColumn<DeliverySchedulingEnumerations, String> DeliveredMaterials;

    // TODO:
    // DEFINE LICENCES JOIN DELIVERERS TABLE AND TABLECOLUMNS

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
        if(date == null) {
            new Alert(Alert.AlertType.ERROR, "Selecione um Local E Data!").show();
            return;
        }
        Timestamp selectedDate = Timestamp.valueOf(date.atStartOfDay());
        if((selectedSite == null) || (selectedDate == null)) {
            new Alert(Alert.AlertType.ERROR, "Selecione um Local E Data!").show();
            return;
        }
        System.out.println(selectedDate);
        System.out.println(selectedSite.getName());

        // TODO:
        // deliveriesList = DA JOIN DE sitesList COM ENTREGAS COM AGENDAMENTOS COM DISCRIMINAÇÕES COM MATERIAIS
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
        SitePickerTable.setItems(sitesList);
        SiteID.setCellValueFactory(new PropertyValueFactory<>("id"));
        SiteName.setCellValueFactory(new PropertyValueFactory<>("name"));
        SiteStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        SiteNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        SiteCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        SiteState.setCellValueFactory(new PropertyValueFactory<>("state"));

        DeliveryViewTable.setItems(deliveriesList);
        DeliveryID.setCellValueFactory(new PropertyValueFactory<>("id"));
        DeliverySchedulingID.setCellValueFactory(new PropertyValueFactory<>("scheduling"));
        DeliveryCompanyCNPJ.setCellValueFactory(new PropertyValueFactory<>("companyCnpj"));
        DeliveredMaterials.setCellValueFactory(new PropertyValueFactory<>("materialsString"));
    }
}
