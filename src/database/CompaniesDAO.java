package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import model.viewtables.Companies;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompaniesDAO {
    public ObservableList<Companies> findAll() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        ObservableList<Companies> cpns = FXCollections.observableArrayList();

        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        String findAllQuery = "select * from Companies";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllQuery);
            while (resultSet.next()) {
                cpns.add(new Companies(resultSet.getString("cpn_cnpj"), resultSet.getString("cpn_name"), resultSet.getString("cpn_fantasy")));
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

    public ObservableList<Companies> findBycnpj(String cnpj) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Companies> rs = FXCollections.observableArrayList();

        String query = "select * from Companies where cpn_cnpj = '" + cnpj + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Companies(resultSet.getString("cpn_cnpj"), resultSet.getString("cpn_name"), resultSet.getString("cpn_fantasy")));
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

    public ObservableList<Companies> findByname(String name) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Companies> rs = FXCollections.observableArrayList();

        String query = "select * from Companies where cpn_name = '" + name + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Companies(resultSet.getString("cpn_cnpj"), resultSet.getString("cpn_name"), resultSet.getString("cpn_fantasy")));
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

    public ObservableList<Companies> findByfantasy(String fantasy) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }
        Statement statement = null;

        ObservableList<Companies> rs = FXCollections.observableArrayList();

        String query = "select * from Companies where cpn_fantasy = '" + fantasy + "'";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs.add(new Companies(resultSet.getString("cpn_cnpj"), resultSet.getString("cpn_name"), resultSet.getString("cpn_fantasy")));
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

    public void insert(Companies ins) throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return;
        }
        Statement statement = null;
        String query = "insert into Companies(cpn_cnpj, cpn_name, cpn_fantasy) values ('" + ins.getCnpj() + "', '" + ins.getName() + "', '" + ins.getFantasy() + "')";

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

    //Returns the "company" with the biggest amount of "deliveries" and how many it is
    public Pair<Companies, Integer> getMostDeliveries() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }

        Statement statement = null;
        Companies rs = null;
        Pair<Companies, Integer> pair = null;

        String query = "select * from (select C.cpn_cnpj, C.cpn_name, C.cpn_fantasy, count(*) as cpn_count from Companies C join Schedulings S on C.cpn_cnpj = S.sch_company join Deliveries D on S.sch_id = D.dlv_scheduling group by C.cpn_cnpj, C.cpn_name, C.cpn_fantasy order by count(*) desc) where rownum = 1";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs = new Companies(resultSet.getString("cpn_cnpj"), resultSet.getString("cpn_name"), resultSet.getString("cpn_fantasy"));
                Integer numDeliveries = resultSet.getInt("cpn_count");
                pair = new Pair<>(rs, numDeliveries);
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
        return pair;
    }

    //Returns the "company" with that delivered the biggest mass of materials
    //sum of all quantity of the material delivered * weight of the material
    public Pair<Companies, Integer> getHeaviest() throws SQLException {
        DatabaseManager dbm = new DatabaseManager();
        Connection connection = dbm.getConnection();
        if (connection == null) {
            System.out.println("Couldn't connect to database");
            return null;
        }

        Statement statement = null;
        Companies rs = null;
        Pair<Companies, Integer> pair = null;

        String query = "select * from (select C.cpn_cnpj, C.cpn_name, C.cpn_fantasy, sum(E.enum_quantity * M.mtr_weight) as cpn_sum from Companies C join Schedulings S on C.cpn_cnpj = S.sch_company join Deliveries D on S.sch_id = D.dlv_scheduling join Enumerations E on D.dlv_id = E.enum_delivery join Materials M on E.enum_material = M.mtr_id group by C.cpn_cnpj, C.cpn_name, C.cpn_fantasy order by sum(E.enum_quantity * M.mtr_weight) desc) where rownum = 1";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                rs = new Companies(resultSet.getString("cpn_cnpj"), resultSet.getString("cpn_name"), resultSet.getString("cpn_fantasy"));
                Integer totalWeight = resultSet.getInt("cpn_sum");
                pair = new Pair<>(rs, totalWeight);
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
        return pair;
    }
}