package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Sites;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NilFu on 19/06/2016.
 */
public class SitesDAO {
    public ObservableList<Sites> findAll() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        ObservableList<Sites> cpns = FXCollections.observableArrayList();

        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        String findAllQuery = "select * from Sites";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllQuery);

            while(resultSet.next()) {
                cpns.add(new Sites( resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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
public ObservableList<Sites> findByid(Integer id) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_id = " + id + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Sites( resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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
	public ObservableList<Sites> findByname(String name) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_name = '" + name + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Sites( resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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
	public ObservableList<Sites> findBystreet(String street) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_street = '" + street + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Sites( resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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
	public ObservableList<Sites> findBynumber(Integer number) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_number = " + number + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Sites( resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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
	public ObservableList<Sites> findBycity(String city) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_city = '" + city + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Sites( resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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
	public ObservableList<Sites> findBystate(String state) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_state = '" + state + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Sites( resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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
	public ObservableList<Sites> findByzip(String zip) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_zip = '" + zip + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Sites( resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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
	public ObservableList<Sites> findBycompany(String company) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Sites> rs = FXCollections.observableArrayList();

        String query = "select * from Sites where site_company = '" + company + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Sites( resultSet.getInt("site_id"), resultSet.getString("site_name"), resultSet.getString("site_street"), resultSet.getInt("site_number"), resultSet.getString("site_city"), resultSet.getString("site_state"), resultSet.getString("site_zip"), resultSet.getString("site_company")));
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