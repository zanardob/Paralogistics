package controller.viewtables;

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

import database.DeliverersDAO;
import model.viewtables.Deliverers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeliverersController implements Initializable {
    @FXML TableView<Deliverers> Table;
    @FXML TableColumn<Deliverers, String> CPF;
    @FXML TableColumn<Deliverers, String> Name;
    @FXML TableColumn<Deliverers, String> RG;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Deliverers> entryList = null;
        try {
            entryList = new DeliverersDAO().findAll();
            System.out.println(entryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Table.setItems(entryList);
        CPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        RG.setCellValueFactory(new PropertyValueFactory<>("rg"));
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
