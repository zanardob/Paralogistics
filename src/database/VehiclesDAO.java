package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entities.Vehicles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NilFu on 19/06/2016.
 */
public class VehiclesDAO {
    public ObservableList<Vehicles> findAll() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        ObservableList<Vehicles> cpns = FXCollections.observableArrayList();

        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        String findAllQuery = "select * from Vehicles";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllQuery);

            while(resultSet.next()) {
                cpns.add(new Vehicles( resultSet.getString("vhc_plate"), resultSet.getTimestamp("vhc_concession_start"), resultSet.getTimestamp("vhc_concession_end")));
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
public ObservableList<Vehicles> findByplate(String plate) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Vehicles> rs = FXCollections.observableArrayList();

        String query = "select * from Vehicles where vhc_plate = '" + plate + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Vehicles( resultSet.getString("vhc_plate"), resultSet.getTimestamp("vhc_concession_start"), resultSet.getTimestamp("vhc_concession_end")));
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
	public ObservableList<Vehicles> findByconcession_start(java.sql.Timestamp concession_start) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Vehicles> rs = FXCollections.observableArrayList();

        String query = "select * from Vehicles where vhc_concession_start = " + concession_start + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Vehicles( resultSet.getString("vhc_plate"), resultSet.getTimestamp("vhc_concession_start"), resultSet.getTimestamp("vhc_concession_end")));
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
	public ObservableList<Vehicles> findByconcession_end(java.sql.Timestamp concession_end) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Vehicles> rs = FXCollections.observableArrayList();

        String query = "select * from Vehicles where vhc_concession_end = " + concession_end + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Vehicles( resultSet.getString("vhc_plate"), resultSet.getTimestamp("vhc_concession_start"), resultSet.getTimestamp("vhc_concession_end")));
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
	public void insert(Vehicles ins) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return;
        }
        Statement statement = null;
        String query = "insert into Vehicles( vhc_plate, vhc_concession_start, vhc_concession_end) values (" + ins.getPlate() + ", " + ins.getConcession_start() + ", " + ins.getConcession_end() + ")";

        try {
            statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(statement != null){
            statement.close();
        }

        if(connection != null) {
            connection.close();
        }
    }
}