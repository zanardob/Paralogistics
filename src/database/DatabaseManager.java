package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    // These variables are used to connect to the Oracle Database; change them if you need
    private static final String CONNECTION = "jdbc:oracle:thin:@grad.icmc.usp.br:15215:orcl";
    private static final String USERNAME = "g8937458";
    private static final String PASSWORD = "g8937458";

    //Creates the connection with the oracle database
    //Must be called every time the database needs to be accessed
    public Connection getConnection() {
        Connection connection = null;
        //Test Driver
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
        }

        //Test connection
        try {
            connection = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }

        return connection;
    }
}
