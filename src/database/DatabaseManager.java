package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by NilFu on 18/06/2016.
 */
public class DatabaseManager {
    private static final String CONNECTION = "jdbc:oracle:thin:@grad.icmc.usp.br:15215:orcl";
    private static final String USERNAME = "n8937250";
    private static final String PASSWORD = "n8937250";

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
        }

        try{
            connection = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }

        return connection;
    }
}
