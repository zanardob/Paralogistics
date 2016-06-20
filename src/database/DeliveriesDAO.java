package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entities.Deliveries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by NilFu on 19/06/2016.
 */
public class DeliveriesDAO {
    public ObservableList<Deliveries> findAll() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        ObservableList<Deliveries> cpns = FXCollections.observableArrayList();

        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        String findAllQuery = "select * from Deliveries";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllQuery);

            while(resultSet.next()) {
                cpns.add(new Deliveries( resultSet.getInt("dlv_id"), resultSet.getInt("dlv_site"), resultSet.getDate("dlv_start"), resultSet.getDate("dlv_end"), resultSet.getInt("dlv_scheduling")));
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
public ObservableList<Deliveries> findByid(Integer id) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Deliveries> rs = FXCollections.observableArrayList();

        String query = "select * from Deliveries where dlv_id = " + id + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Deliveries( resultSet.getInt("dlv_id"), resultSet.getInt("dlv_site"), resultSet.getDate("dlv_start"), resultSet.getDate("dlv_end"), resultSet.getInt("dlv_scheduling")));
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
	public ObservableList<Deliveries> findBysite(Integer site) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Deliveries> rs = FXCollections.observableArrayList();

        String query = "select * from Deliveries where dlv_site = " + site + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Deliveries( resultSet.getInt("dlv_id"), resultSet.getInt("dlv_site"), resultSet.getDate("dlv_start"), resultSet.getDate("dlv_end"), resultSet.getInt("dlv_scheduling")));
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
	public ObservableList<Deliveries> findBystart(java.sql.Date start) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Deliveries> rs = FXCollections.observableArrayList();

        String query = "select * from Deliveries where dlv_start = " + start + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Deliveries( resultSet.getInt("dlv_id"), resultSet.getInt("dlv_site"), resultSet.getDate("dlv_start"), resultSet.getDate("dlv_end"), resultSet.getInt("dlv_scheduling")));
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
	public ObservableList<Deliveries> findByend(java.sql.Date end) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Deliveries> rs = FXCollections.observableArrayList();

        String query = "select * from Deliveries where dlv_end = " + end + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Deliveries( resultSet.getInt("dlv_id"), resultSet.getInt("dlv_site"), resultSet.getDate("dlv_start"), resultSet.getDate("dlv_end"), resultSet.getInt("dlv_scheduling")));
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
	public ObservableList<Deliveries> findByscheduling(Integer scheduling) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Deliveries> rs = FXCollections.observableArrayList();

        String query = "select * from Deliveries where dlv_scheduling = " + scheduling + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Deliveries( resultSet.getInt("dlv_id"), resultSet.getInt("dlv_site"), resultSet.getDate("dlv_start"), resultSet.getDate("dlv_end"), resultSet.getInt("dlv_scheduling")));
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