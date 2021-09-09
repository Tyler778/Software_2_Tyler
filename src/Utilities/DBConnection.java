/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author tyler
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tyler
 */
public class DBConnection {
    
    //JDBC URL parts
    private static final String protocol = "jdbc";
    private static final String venderName = ":mysql:";
    private static final String serverName = "//3.227.166.251:3306/";
    private static final String dbName = "WJ07nX6";
    
    // JDBC URL
    private static final String jdbcURL = protocol + venderName + serverName + dbName;
    
    //Driver and Connection Interface Reference
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;
    //Username
    private static final String username = "U07nX6";
    
    //Password
    private static final String password = "53689081350";
    
    //ONLY CALL START CONNECTION ONCE
    public static Connection startConnection() {
        try {
            
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection Successful");
        }
        catch(ClassNotFoundException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }
        catch(SQLException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        return conn;
        
    }
    
    public static Connection getConnection() {
        return conn;
    }
    
    public static void closeConnection() {
        
        try {
            conn.close();
            System.out.println("Connection closed"); 
        } 
        catch(Exception e) {
            //nothing
        }
        
        
    }
} 