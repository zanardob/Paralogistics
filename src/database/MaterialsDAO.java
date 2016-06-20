package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.viewtables.Materials;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by NilFu on 19/06/2016.
 */
public class MaterialsDAO {
    public ObservableList<Materials> findAll() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        ObservableList<Materials> cpns = FXCollections.observableArrayList();

        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        String findAllQuery = "select * from Materials";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllQuery);

            while(resultSet.next()) {
                cpns.add(new Materials( resultSet.getInt("mtr_id"), resultSet.getString("mtr_description"), resultSet.getString("mtr_weight"), resultSet.getString("mtr_dimension")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(statement != null){
            statement.close();
        }

        if(connection != null) {
            connection.close();
        }
        return cpns;
    }
public ObservableList<Materials> findByid(Integer id) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Materials> rs = FXCollections.observableArrayList();

        String query = "select * from Materials where mtr_id = " + id + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Materials( resultSet.getInt("mtr_id"), resultSet.getString("mtr_description"), resultSet.getString("mtr_weight"), resultSet.getString("mtr_dimensions")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(statement != null){
			statement.close();
		}

		if(connection != null) {
			connection.close();
		}
		return rs;
	}
	public ObservableList<Materials> findBydescription(String description) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Materials> rs = FXCollections.observableArrayList();

        String query = "select * from Materials where mtr_description = '" + description + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Materials( resultSet.getInt("mtr_id"), resultSet.getString("mtr_description"), resultSet.getString("mtr_weight"), resultSet.getString("mtr_dimensions")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(statement != null){
			statement.close();
		}

		if(connection != null) {
			connection.close();
		}
		return rs;
	}
	public ObservableList<Materials> findByweight(String weight) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Materials> rs = FXCollections.observableArrayList();

        String query = "select * from Materials where mtr_weight = '" + weight + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Materials( resultSet.getInt("mtr_id"), resultSet.getString("mtr_description"), resultSet.getString("mtr_weight"), resultSet.getString("mtr_dimensions")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(statement != null){
			statement.close();
		}

		if(connection != null) {
			connection.close();
		}
		return rs;
	}
	public ObservableList<Materials> findBydimensions(String dimensions) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Materials> rs = FXCollections.observableArrayList();

        String query = "select * from Materials where mtr_dimensions = '" + dimensions + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Materials( resultSet.getInt("mtr_id"), resultSet.getString("mtr_description"), resultSet.getString("mtr_weight"), resultSet.getString("mtr_dimensions")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(statement != null){
			statement.close();
		}

		if(connection != null) {
			connection.close();
		}
		return rs;
	}
	}