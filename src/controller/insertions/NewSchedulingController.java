package controller.insertions;

import database.CompaniesDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.viewtables.Companies;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class NewSchedulingController implements Initializable{

    @FXML TableView<Companies> CompanyPickerTable;

    @FXML TableColumn<Companies, String> CompanyCNPJ;
    @FXML TableColumn<Companies, String> CompanyName;
    @FXML TableColumn<Companies, String> CompanyFantasy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Companies> entryList = null;
        try {
            entryList = new CompaniesDAO().findAll();
            System.out.println(entryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CompanyPickerTable.setItems(entryList);
        CompanyCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        CompanyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        CompanyFantasy.setCellValueFactory(new PropertyValueFactory<>("fantasy"));
    }

    public void Cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) CompanyPickerTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainmenu.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SearchCompanies(ActionEvent actionEvent) {

    }

    public void Confirm(ActionEvent actionEvent) {
        Companies selectedCompany = CompanyPickerTable.getSelectionModel().getSelectedItem();
        if(selectedCompany == null) {
            new Alert(Alert.AlertType.ERROR, "Select a Company").show();
            return;
        }

        Stage stage = (Stage) CompanyPickerTable.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/insertions/newscheduling2.fxml"));
            Parent root = loader.load();
            NewScheduling2Controller controller = loader.getController();
            controller.setCompany(selectedCompany);
            controller.clearTables();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
