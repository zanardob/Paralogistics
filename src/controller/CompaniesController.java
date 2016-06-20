package controller;

import database.CompaniesDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Companies;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class CompaniesController implements Initializable{
    @FXML
    TableView<Companies> Table;

    @FXML
    TableColumn<Companies, String> CNPJ;

    @FXML
    TableColumn<Companies, String> Name;

    @FXML
    TableColumn<Companies, String> Fantasy;

    public void GotoMainMenu(ActionEvent actionEvent) {
        Stage stage = (Stage) Table.getScene().getWindow();
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
        ObservableList<Companies> entryList = null;
        try {
            entryList = new CompaniesDAO().findAll();
            System.out.println(entryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Table.setItems(entryList);
        CNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Fantasy.setCellValueFactory(new PropertyValueFactory<>("fantasy"));
    }
}
