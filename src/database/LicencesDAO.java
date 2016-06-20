package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entities.Licences;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NilFu on 19/06/2016.
 */
public class LicencesDAO {
    public ObservableList<Licences> findAll() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        ObservableList<Licences> cpns = FXCollections.observableArrayList();

        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        String findAllQuery = "select * from Licences";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllQuery);

            while(resultSet.next()) {
                cpns.add(new Licences( resultSet.getString("lcc_deliverer"), resultSet.getInt("lcc_scheduling"), resultSet.getString("lcc_vehicle")));
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
public ObservableList<Licences> findBydeliverer(String deliverer) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Licences> rs = FXCollections.observableArrayList();

        String query = "select * from Licences where lcc_deliverer = '" + deliverer + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Licences( resultSet.getString("lcc_deliverer"), resultSet.getInt("lcc_scheduling"), resultSet.getString("lcc_vehicle")));
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
	public ObservableList<Licences> findByscheduling(Integer scheduling) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Licences> rs = FXCollections.observableArrayList();

        String query = "select * from Licences where lcc_scheduling = " + scheduling + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Licences( resultSet.getString("lcc_deliverer"), resultSet.getInt("lcc_scheduling"), resultSet.getString("lcc_vehicle")));
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
	public ObservableList<Licences> findByvehicle(String vehicle) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Licences> rs = FXCollections.observableArrayList();

        String query = "select * from Licences where lcc_vehicle = '" + vehicle + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Licences( resultSet.getString("lcc_deliverer"), resultSet.getInt("lcc_scheduling"), resultSet.getString("lcc_vehicle")));
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
	public void insert(Licences ins) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return;
        }
        Statement statement = null;
        String query = "insert into Licences( lcc_deliverer, lcc_scheduling, lcc_vehicle) values (" + ins.getDeliverer() + ", " + ins.getScheduling() + ", " + ins.getVehicle() + ")";

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