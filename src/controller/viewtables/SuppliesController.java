package controller.viewtables;

import database.SuppliesDAO;
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
import model.entities.Supplies;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by NilFu on 19/06/2016.
 */
public class SuppliesController implements Initializable{
    @FXML
    TableView<Supplies> Table;

    @FXML
    TableColumn<Supplies, Integer> MaterialID;

    @FXML
    TableColumn<Supplies, String> CompanyCNPJ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Supplies> entryList = null;
        try {
            entryList = new SuppliesDAO().findAll();
            System.out.println(entryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Table.setItems(entryList);
        MaterialID.setCellValueFactory(new PropertyValueFactory<>("material"));
        CompanyCNPJ.setCellValueFactory(new PropertyValueFactory<>("company"));
    }

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

}
