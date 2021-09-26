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
    private static final String serverName = "//137.184.136.251/";
    private static final String dbName = "WJ07nX6";
    
    // JDBC URL
    private static final String jdbcURL = protocol + venderName + serverName + dbName;
    
    //Driver and Connection Interface Reference
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;
    //Username
    private static final String username = "tyler50";
    
    //Password
    private static final String password = "49814981";
    
    //ONLY CALL START CONNECTION ONCE
    
    /**
     * Method returns a connection using static values that are put into the JDBC driver and obtains the connection from
     * @return 
     */
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
    /**
     * Returns the active SQL connection obtained from the JDBC driver.  Note it does not reconnect to the database, only returns the current connection
     * @return 
     */
    public static Connection getConnection() {
        return conn;
    }
    /**
     * Terminates the Connection to the database
     */
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