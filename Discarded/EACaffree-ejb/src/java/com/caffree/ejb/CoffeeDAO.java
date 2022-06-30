/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.caffree.ejb;

/**
 *
 * @author 37409
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 * @author www.codejava.net
 *
 */
public class CoffeeDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public CoffeeDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean insertBook(Coffee coffee) throws SQLException {
        String sql = "INSERT INTO coffee (u_name, c_name, sugar, temperature) VALUES (?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, coffee.getU_name());
        statement.setString(2, coffee.getC_name());
        statement.setInt(3, (int) coffee.getSugar());
        statement.setInt(4, (int) coffee.getTemperature());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Coffee> listAllCoffees() throws SQLException {
        List<Coffee> listCoffee = new ArrayList<>();
         
        String sql = "SELECT * FROM coffee";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String u_name = resultSet.getString("u_name");
            String c_name = resultSet.getString("c_name");
            int sugar = resultSet.getInt("sugar");
            int temperature = resultSet.getInt("temperature");
             
            Coffee coffee = new Coffee(id, u_name, c_name, sugar, temperature);
            listCoffee.add(coffee);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listCoffee;
    }
     
    public boolean deleteBook(Coffee coffee) throws SQLException {
        String sql = "DELETE FROM coffee where id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, coffee.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateBook(Coffee coffee) throws SQLException {
        String sql = "UPDATE coffee SET u_name = ?, c_name = ?, sugar = ?, temperature = ?";
        sql += " WHERE id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, coffee.getU_name());
        statement.setString(2, coffee.getC_name());
        statement.setInt(3, (int) coffee.getSugar());
        statement.setInt(4, (int) coffee.getTemperature());
        statement.setInt(4, coffee.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Coffee getBook(int id) throws SQLException {
        Coffee coffee = null;
        String sql = "SELECT * FROM book WHERE book_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {     
            String u_name = resultSet.getString("u_name");
            String c_name = resultSet.getString("c_name");
            int sugar = resultSet.getInt("sugar");
            int temperature = resultSet.getInt("temperature");
             
            coffee = new Coffee(id, u_name, c_name, sugar, temperature);
        }
         
        resultSet.close();
        statement.close();
         
        return coffee;
    }
}

