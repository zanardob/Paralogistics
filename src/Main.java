import controller.MainMenuController;
import database.CompaniesDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    public static void main(String[] argv) {
        launch(argv);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        System.out.println(new CompaniesDAO().findAll());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainmenu.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Paralogistics");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
