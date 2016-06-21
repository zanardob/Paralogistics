package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.viewtables.Periods;
import model.viewtables.Receivers;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NilFu on 19/06/2016.
 */
public class PeriodsDAO {
    public ObservableList<Periods> findAll() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        ObservableList<Periods> cpns = FXCollections.observableArrayList();

        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        String findAllQuery = "SELECT * FROM Periods";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllQuery);

            while (resultSet.next()) {
                cpns.add(new Periods(resultSet.getInt("prd_site"), resultSet.getTimestamp("prd_start"), resultSet.getTimestamp("prd_end"), resultSet.getString("prd_receiver")));
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

    public ObservableList<Periods> findBysite(Integer site) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Periods> rs = FXCollections.observableArrayList();

        String query = "select * from Periods where prd_site = " + site + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Periods(resultSet.getInt("prd_site"), resultSet.getTimestamp("prd_start"), resultSet.getTimestamp("prd_end"), resultSet.getString("prd_receiver")));
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

    public ObservableList<Periods> findBystart(java.sql.Timestamp start) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Periods> rs = FXCollections.observableArrayList();

        String query = "select * from Periods where prd_start = " + start + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Periods(resultSet.getInt("prd_site"), resultSet.getTimestamp("prd_start"), resultSet.getTimestamp("prd_end"), resultSet.getString("prd_receiver")));
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

    public ObservableList<Periods> findByend(java.sql.Timestamp end) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Periods> rs = FXCollections.observableArrayList();

        String query = "select * from Periods where prd_end = " + end + "";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Periods(resultSet.getInt("prd_site"), resultSet.getTimestamp("prd_start"), resultSet.getTimestamp("prd_end"), resultSet.getString("prd_receiver")));
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

    public ObservableList<Periods> findByreceiver(String receiver) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Periods> rs = FXCollections.observableArrayList();

        String query = "select * from Periods where prd_receiver = '" + receiver + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Periods(resultSet.getInt("prd_site"), resultSet.getTimestamp("prd_start"), resultSet.getTimestamp("prd_end"), resultSet.getString("prd_receiver")));
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

    public void insert(Periods ins) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return;
        }
        Statement statement = null;
        String query = "insert into Periods( prd_site, prd_start, prd_end, prd_receiver) values (" + ins.getSite() + ", " + ins.getStart() + ", " + ins.getEnd() + ", " + ins.getReceiver() + ")";

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

    public Receivers getReceiver(Integer site, Timestamp start, Timestamp end) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }

        Statement statement = null;
        Receivers rs = null;

        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start);
        String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end);

        String query = "select R.rcv_cpf, R.rcv_name, R.rcv_rg from Periods P join Receivers R on P.prd_receiver = R.rcv_cpf where (P.prd_site = '" + site + "' and P.prd_start = TO_DATE('" + startTime + "' , 'yyyy-mm-dd hh24:mi:ss') and P.prd_end = TO_DATE('" + endTime + "' , 'yyyy-mm-dd hh24:mi:ss'))";
        System.out.println(query);

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs = new Receivers(resultSet.getString("rcv_cpf"), resultSet.getString("rcv_name"), resultSet.getString("rcv_rg"));
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