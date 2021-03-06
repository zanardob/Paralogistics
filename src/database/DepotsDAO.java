package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.viewtables.Depots;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DepotsDAO {
    public ObservableList<Depots> findAll() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        ObservableList<Depots> cpns = FXCollections.observableArrayList();

        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        String findAllQuery = "select * from Depots";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllQuery);

            while (resultSet.next()) {
                cpns.add(new Depots(resultSet.getInt("dpt_site"), resultSet.getInt("dpt_number"), resultSet.getString("dpt_capacity"), resultSet.getString("dpt_dimension")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
        return cpns;
    }

    public ObservableList<Depots> findBysite(Integer site) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Depots> rs = FXCollections.observableArrayList();

        String query = "select * from Depots where dpt_site = " + site + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Depots(resultSet.getInt("dpt_site"), resultSet.getInt("dpt_number"), resultSet.getString("dpt_capacity"), resultSet.getString("dpt_dimension")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
        return rs;
    }

    public ObservableList<Depots> findBynumber(Integer number) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Depots> rs = FXCollections.observableArrayList();

        String query = "select * from Depots where dpt_number = " + number + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Depots(resultSet.getInt("dpt_site"), resultSet.getInt("dpt_number"), resultSet.getString("dpt_capacity"), resultSet.getString("dpt_dimension")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
        return rs;
    }

    public ObservableList<Depots> findBycapacity(String capacity) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Depots> rs = FXCollections.observableArrayList();

        String query = "select * from Depots where dpt_capacity = '" + capacity + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Depots(resultSet.getInt("dpt_site"), resultSet.getInt("dpt_number"), resultSet.getString("dpt_capacity"), resultSet.getString("dpt_dimension")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
        return rs;
    }

    public ObservableList<Depots> findBydimension(String dimension) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Depots> rs = FXCollections.observableArrayList();

        String query = "select * from Depots where dpt_dimension = '" + dimension + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Depots(resultSet.getInt("dpt_site"), resultSet.getInt("dpt_number"), resultSet.getString("dpt_capacity"), resultSet.getString("dpt_dimension")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
        return rs;
    }

    public void insert(Depots ins) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return;
        }
        Statement statement = null;
        String query = "insert into Depots( dpt_site, dpt_number, dpt_capacity, dpt_dimension) values (" + ins.getSite() + ", " + ins.getNumber() + ", " + ins.getCapacity() + ", '" + ins.getDimension() + "')";

        try {
            statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }
}