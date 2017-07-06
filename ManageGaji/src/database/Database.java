/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author markibid
 */
public class Database {
    private static Connection conn;
    
    private static String dbName = "manage_gaji";
    private static String dbUserName = "root";
    private static String dbPassWord = "";
    
    public static Connection GetConnection() throws SQLException{        
        if(conn == null){
            new Driver();

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, dbUserName, dbPassWord);
        }
        
        return conn;
    }
    
    public static void insertData(String table, String atr, String value){
        try{
            Statement stmt = (Statement) Database.GetConnection().createStatement();
            String query = "insert into " +table+ " " +atr+" VALUES " + value;
            stmt.executeUpdate(query);
            stmt.close();
        } catch(Exception t){
            t.printStackTrace();
        }
    }
    
    public static ResultSet selectData(String table){
        ResultSet rs = null;
        try{
            Statement stmt = (Statement) Database.GetConnection().createStatement();
            String query = "select *  from " + table;
            rs = stmt.executeQuery(query);
        } catch(Exception t){
            t.printStackTrace();
        }
        return rs;
    }
    
    public static ResultSet searchData(String table, String search){
        ResultSet rs = null;
        try{
            Statement stmt = (Statement) Database.GetConnection().createStatement();
            String query = "select * from " + table +" where " + search;
            rs = stmt.executeQuery(query);
        } catch(Exception t){
            t.getMessage();
        }
        return rs;
    }
    
    public static void deleteData(String table, Integer id){
        try{
            Statement stmt = (Statement) Database.GetConnection().createStatement();
            String query = "delete from " + table + " where id=" + id;
            stmt.executeUpdate(query);
            stmt.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void updateData(String table, String set, Integer id){
        try{
            Statement stmt = (Statement) Database.GetConnection().createStatement();
            String query = "update " + table + " set " + set + " where id=" + id;
            stmt.executeUpdate(query);
            stmt.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
