package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.insertions.MaterialQuantity;
import model.viewtables.Enumerations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnumerationsDAO {
    public ObservableList<Enumerations> findAll() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        ObservableList<Enumerations> cpns = FXCollections.observableArrayList();

        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        String findAllQuery = "SELECT * FROM Enumerations";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllQuery);

            while (resultSet.next()) {
                cpns.add(new Enumerations(resultSet.getInt("enum_delivery"), resultSet.getInt("enum_material"), resultSet.getInt("enum_quantity")));
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

    public ObservableList<Enumerations> findBydelivery(Integer delivery) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Enumerations> rs = FXCollections.observableArrayList();

        String query = "select * from Enumerations where enum_delivery = " + delivery + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Enumerations(resultSet.getInt("enum_delivery"), resultSet.getInt("enum_material"), resultSet.getInt("enum_quantity")));
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

    public ObservableList<Enumerations> findBymaterial(Integer material) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Enumerations> rs = FXCollections.observableArrayList();

        String query = "select * from Enumerations where enum_material = " + material + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Enumerations(resultSet.getInt("enum_delivery"), resultSet.getInt("enum_material"), resultSet.getInt("enum_quantity")));
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

    public ObservableList<Enumerations> findByquantity(Integer quantity) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Enumerations> rs = FXCollections.observableArrayList();

        String query = "select * from Enumerations where enum_quantity = " + quantity + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Enumerations(resultSet.getInt("enum_delivery"), resultSet.getInt("enum_material"), resultSet.getInt("enum_quantity")));
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

    public void insert(Enumerations ins) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return;
        }
        Statement statement = null;
        String query = "insert into Enumerations( enum_delivery, enum_material, enum_quantity) values (" + ins.getDelivery() + ", " + ins.getMaterial() + ", " + ins.getQuantity() + ")";

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

    //Returns all Materials of a certain delivery using the "Id"
    public ObservableList<MaterialQuantity> getMaterials(Integer delivery) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<MaterialQuantity> rs = FXCollections.observableArrayList();

        String query = "select M.mtr_id, M.mtr_description, M.mtr_weight, M.mtr_dimension, E.enum_quantity from Enumerations E join Materials M on E.enum_material = M.mtr_id where E.enum_delivery = " + delivery;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new MaterialQuantity(resultSet.getInt("mtr_id"), resultSet.getString("mtr_description"), resultSet.getString("mtr_weight"), resultSet.getString("mtr_dimension"), resultSet.getInt("enum_quantity")));
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
}