package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.viewtables.Sites;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SitesDAO {
    public ObservableList<Sites> findAll() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        ObservableList<Sites> cpns = FXCollections.observableArrayList();

        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        String findAllQuery = "SELECT * FROM Sites";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllQuery);

            while (resultSet.next()) {
                cpns.add(new Sites(resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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

    public Sites findByid(Integer id) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }

        Statement statement = null;
        Sites rs = null;

        String query = "select * from Sites where site_id = " + id + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs = new Sites(resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company"));
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

    public ObservableList<Sites> findByname(String name) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_name = '" + name + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Sites(resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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

    public ObservableList<Sites> findBystreet(String street) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_street = '" + street + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Sites(resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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

    public ObservableList<Sites> findBynumber(Integer number) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_number = " + number + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Sites(resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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

    public ObservableList<Sites> findBycity(String city) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_city = '" + city + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Sites(resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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

    public ObservableList<Sites> findBystate(String state) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_state = '" + state + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Sites(resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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

    public ObservableList<Sites> findByzip(String zip) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_zip = '" + zip + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Sites(resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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

    public ObservableList<Sites> findBycompany(String company) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_company = '" + company + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Sites(resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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

    public void insert(Sites ins) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return;
        }
        Statement statement = null;
        String query = "insert into Sites( site_id, site_name, site_street, site_number, site_city, site_state" +
                ", site_zip, site_company) values (" + ins.getId() + ", '" + ins.getName() + "', '" + ins.getStreet() +
                "', " + ins.getNumber() + ", '" + ins.getCity() + "', '" + ins.getState() + "', '" + ins.getZip() + "', '" +
                ins.getCompany() + "')";

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