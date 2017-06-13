package connection;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;
import config.App;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author markibid
 */
public class Connect {
       
    public static Connection con;
    
    public static Connection GetConnection() throws SQLException{
        if(con == null){
            new Driver();
            
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + App.dbName, App.userName, App.passWord);
        }
        return con;
    }
    
    public static void insertData(String table, String atr, String value){
        try{
            Statement stmt = (Statement) Connect.GetConnection().createStatement();
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
            Statement stmt = (Statement) Connect.GetConnection().createStatement();
            String query = "select *  from " + table;
            rs = stmt.executeQuery(query);
        } catch(Exception t){
            t.printStackTrace();
        }
        return rs;
    }
    
    public static void deleteData(String table, Integer id){
        try{
            Statement stmt = (Statement) Connect.GetConnection().createStatement();
            String query = "delete from " + table + " where id=" + id;
            stmt.executeUpdate(query);
            stmt.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void updateData(String table, String set, Integer id){
        try{
            Statement stmt = (Statement) Connect.GetConnection().createStatement();
            String query = "update " + table + " set " + set + " where id=" + id;
            stmt.executeUpdate(query);
            stmt.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
