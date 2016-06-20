package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entities.Schedulings;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NilFu on 19/06/2016.
 */
public class SchedulingsDAO {
    public ObservableList<Schedulings> findAll() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        ObservableList<Schedulings> cpns = FXCollections.observableArrayList();

        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        String findAllQuery = "select * from Schedulings";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllQuery);

            while(resultSet.next()) {
                cpns.add(new Schedulings( resultSet.getInt("sch_id"), resultSet.getString("sch_company")));
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
public ObservableList<Schedulings> findByid(Integer id) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Schedulings> rs = FXCollections.observableArrayList();

        String query = "select * from Schedulings where sch_id = " + id + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Schedulings( resultSet.getInt("sch_id"), resultSet.getString("sch_company")));
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
	public ObservableList<Schedulings> findBycompany(String company) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;
		
		ObservableList<Schedulings> rs = FXCollections.observableArrayList();

        String query = "select * from Schedulings where sch_company = '" + company + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()) {
				rs.add(new Schedulings( resultSet.getInt("sch_id"), resultSet.getString("sch_company")));
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
	public void insert(Schedulings ins) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return;
        }
        Statement statement = null;
        String query = "insert into Schedulings( sch_id, sch_company) values (" + ins.getId() + ", " + ins.getCompany() + ")";

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