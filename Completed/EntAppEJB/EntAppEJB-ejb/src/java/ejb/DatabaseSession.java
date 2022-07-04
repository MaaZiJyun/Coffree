/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 37409
 */
@Stateless
public class DatabaseSession implements DatabaseSessionRemote, DatabaseSessionLocal {

    private Connection conn = null;
//    private Statement st = null;
//    private ResultSet rs = null;
    
    private void connect(){
        System.out.println("DATABASE: Database Connecting");
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "coffree";
        String username = "root";
        String pswd = "";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, username, pswd);
            System.out.println("DATABASE: Database Connected");
        } //try  
        catch (Exception e) {
            System.out.println(e);
        } //catch  
    }
    
    private void disconnect(){
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    @Override
    public boolean insert(String u_name, String c_name, String sugar, String temperature) {
        connect();
        try {
            String sql = "INSERT INTO coffee (u_name, c_name, sugar, temperature) VALUES (?, ?, ?, ?)";
 
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, u_name);
            statement.setString(2, c_name);
            statement.setString(3, sugar);
            statement.setString(4, temperature);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("DATABASE: A new user was inserted successfully!");
            }else{
                System.err.println("DATABASE: A new user was inserted failed!");
            }
            disconnect();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    @Override
    public boolean update(String id, String u_name, String c_name, String sugar, String temperature) {
        connect();
        try {
            String sql = "UPDATE coffee SET u_name=?, c_name=?, sugar=?, temperature=? WHERE id=?";
 
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, u_name);
            statement.setString(2, c_name);
            statement.setString(3, sugar);
            statement.setString(4, temperature);
            statement.setString(5, id);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("DATABASE: An existing user was updated successfully!");
            }else{
                System.err.println("DATABASE: An existing user was updated failed!");
            }
            disconnect();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    @Override
    public boolean delete(String id){
        connect();
        try{
            String sql = "DELETE FROM coffee WHERE id=?";
 
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("DATABASE: A user was deleted successfully!");
            }else{
                System.err.println("DATABASE: A user was deleted failed!");
            }
            disconnect();
            return true;
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
    
    public ArrayList<Map<String, String>> selectAll(){
        connect();
        
        Map<String, String> coffee = new HashMap<>();
        ArrayList<Map<String, String>> coffeeList = new ArrayList<>();
        
        try{
            
            String sql = "SELECT * FROM coffee";
 
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                coffee = new HashMap<>();
                
                String id = result.getString("id");
                String u_name = result.getString("u_name");
                String c_name = result.getString("c_name");
                String sugar = result.getString("sugar");
                String temperature = result.getString("temperature");
                
                coffee.put("id", id);
                coffee.put("u_name", u_name);
                coffee.put("c_name", c_name);
                coffee.put("sugar", sugar);
                coffee.put("temperature", temperature);
                
                coffeeList.add(coffee);
            }
            
            disconnect();
            return coffeeList;
        }catch(SQLException e){
            System.out.println(e);
            return coffeeList;
        }
    }
    
    @Override
    public Map<String, String> selectById(String id){
        connect();
        
        Map<String, String> coffee = new HashMap<>();
        
        try{
            
            String sql = "SELECT * FROM coffee WHERE id=?";
 
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {     
                coffee = new HashMap<>();
                
                String _id = result.getString("id");
                String _u_name = result.getString("u_name");
                String _c_name = result.getString("c_name");
                String _sugar = result.getString("sugar");
                String _temperature = result.getString("temperature");
                
                coffee.put("id", _id);
                coffee.put("u_name", _u_name);
                coffee.put("c_name", _c_name);
                coffee.put("sugar", _sugar);
                coffee.put("temperature", _temperature);
            }
            
            disconnect();
            return coffee;
        }catch(SQLException e){
            System.out.println(e);
            return coffee;
        }
    }
    
    
    @Override
    public ArrayList<Map<String, String>> selectByName(String searchName){
        connect();
        
        Map<String, String> coffee = new HashMap<>();
        ArrayList<Map<String, String>> coffeeList = new ArrayList<>();
        
        try{
            
            String sql = "SELECT * FROM coffee WHERE u_name=?";
 
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, searchName);
            ResultSet result = statement.executeQuery();

            while (result.next()){
                coffee = new HashMap<>();
                
                String id = result.getString("id");
                String u_name = result.getString("u_name");
                String c_name = result.getString("c_name");
                String sugar = result.getString("sugar");
                String temperature = result.getString("temperature");
                
                coffee.put("id", id);
                coffee.put("u_name", u_name);
                coffee.put("c_name", c_name);
                coffee.put("sugar", sugar);
                coffee.put("temperature", temperature);
                
                coffeeList.add(coffee);
            }
            
            disconnect();
            return coffeeList;
        }catch(SQLException e){
            System.out.println(e);
            return coffeeList;
        }
    }
}
