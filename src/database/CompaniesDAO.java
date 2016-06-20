package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.viewtables.Companies;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NilFu on 19/06/2016.
 */
public class CompaniesDAO {
    public ObservableList<Companies> findAll() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        ObservableList<Companies> cpns = FXCollections.observableArrayList();

        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        String findAllQuery = "select * from Companies";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllQuery);

            while(resultSet.next()) {
                cpns.add(new Companies( resultSet.getString("cpn_cnpj"), resultSet.getString("cpn_name"), resultSet.getString("cpn_fantasy")));
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
public ObservableList<Companies> findBycnpj(String cnpj) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Companies> rs = FXCollections.observableArrayList();

        String query = "select * from Companies where cpn_cnpj = '" + cnpj + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Companies( resultSet.getString("cpn_cnpj"), resultSet.getString("cpn_name"), resultSet.getString("cpn_fantasy")));
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
	public ObservableList<Companies> findByname(String name) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Companies> rs = FXCollections.observableArrayList();

        String query = "select * from Companies where cpn_name = '" + name + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Companies( resultSet.getString("cpn_cnpj"), resultSet.getString("cpn_name"), resultSet.getString("cpn_fantasy")));
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
	public ObservableList<Companies> findByfantasy(String fantasy) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Companies> rs = FXCollections.observableArrayList();

        String query = "select * from Companies where cpn_fantasy = '" + fantasy + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Companies( resultSet.getString("cpn_cnpj"), resultSet.getString("cpn_name"), resultSet.getString("cpn_fantasy")));
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
	public void insert(Companies ins) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return;
        }
        Statement statement = null;
        String query = "insert into Companies( cpn_cnpj, cpn_name, cpn_fantasy) values (" + ins.getCnpj() + ", " + ins.getName() + ", " + ins.getFantasy() + ")";

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