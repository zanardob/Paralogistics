package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.viewtables.Deliveries;
import model.viewtables.Schedulings;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
                cpns.add(new Deliveries( resultSet.getInt("dlv_id"), resultSet.getInt("dlv_site"), resultSet.getTimestamp("dlv_start"), resultSet.getTimestamp("dlv_end"), resultSet.getInt("dlv_scheduling")));
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
				rs.add(new Deliveries( resultSet.getInt("dlv_id"), resultSet.getInt("dlv_site"), resultSet.getTimestamp("dlv_start"), resultSet.getTimestamp("dlv_end"), resultSet.getInt("dlv_scheduling")));
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
				rs.add(new Deliveries( resultSet.getInt("dlv_id"), resultSet.getInt("dlv_site"), resultSet.getTimestamp("dlv_start"), resultSet.getTimestamp("dlv_end"), resultSet.getInt("dlv_scheduling")));
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
	public ObservableList<Deliveries> findBystart(java.sql.Timestamp start) throws SQLException {
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
				rs.add(new Deliveries( resultSet.getInt("dlv_id"), resultSet.getInt("dlv_site"), resultSet.getTimestamp("dlv_start"), resultSet.getTimestamp("dlv_end"), resultSet.getInt("dlv_scheduling")));
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
	public ObservableList<Deliveries> findByend(java.sql.Timestamp end) throws SQLException {
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
				rs.add(new Deliveries( resultSet.getInt("dlv_id"), resultSet.getInt("dlv_site"), resultSet.getTimestamp("dlv_start"), resultSet.getTimestamp("dlv_end"), resultSet.getInt("dlv_scheduling")));
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
				rs.add(new Deliveries( resultSet.getInt("dlv_id"), resultSet.getInt("dlv_site"), resultSet.getTimestamp("dlv_start"), resultSet.getTimestamp("dlv_end"), resultSet.getInt("dlv_scheduling")));
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
	public void insert(Deliveries ins) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if(connection == null) {
            System.out.println("Couldn't connect to database");
            return;
        }
        Statement statement = null;
        String query = "insert into Deliveries( dlv_id, dlv_site, dlv_start, dlv_end, dlv_scheduling) values (" + ins.getId() + ", " + ins.getSite() + ", " + ins.getStart() + ", " + ins.getEnd() + ", " + ins.getScheduling() + ")";

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

    public Integer insertReturnId(Deliveries ins) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ins.getStart());
        String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ins.getEnd());

        String query = "begin insert into Deliveries(dlv_id, dlv_site, dlv_start, dlv_end, dlv_scheduling) values (" + ins.getId() +
                ", " + ins.getSite() + ", TO_DATE('" + startTime + "' , 'yyyy-mm-dd hh24:mi:ss') , TO_DATE('" + endTime + "' , 'yyyy-mm-dd hh24:mi:ss') , " + ins.getScheduling() + ") " +
                "returning dlv_id into ?; end;";

        System.out.println(query);
        CallableStatement cs = connection.prepareCall(query);
        cs.registerOutParameter(1, OracleTypes.NUMBER);
        cs.execute();

        Integer ret = cs.getInt(1);
        System.out.println(cs.getInt(1));

        if (cs != null) {
            cs.close();
        }

        if (connection != null) {
            connection.close();
        }

        return ret;
    }
}