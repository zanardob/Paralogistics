import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] argv) {

        System.out.println("-------- Oracle JDBC Connection Testing ------");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't find Oracle JDBC Driver.");
            e.printStackTrace();
            return;
        }
        System.out.println("Oracle JDBC Driver Registered!");

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@grad.icmc.usp.br:15215:orcl", "n8937250", "n8937250");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control of your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

}
