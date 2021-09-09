/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;


import java.sql.Connection;
//import com.mysql.cj.xdevapi.Statement;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author tyler
 */
public class DBQuery {
    //Statement reference
    private static Statement statement;
    
    
    // Create statement object
    public static void setStatement(Connection c) throws SQLException {
        statement = c.createStatement();
    }
    
    
    // Return Statement object
    public static Statement getStatement() {
        return statement;
    }
}
