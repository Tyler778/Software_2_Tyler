/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Utilities.DBConnection;
import Utilities.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import model.Users;

/**
 *
 * @author tyler
 */
public class DBCredentials {
    private static ObservableList<Users>allUsers = FXCollections.observableArrayList();
    
    
    public static ObservableList<Users> loadUsers () throws SQLException {
        DBQuery.setStatement(DBConnection.getConnection());
        
        Statement statement = DBQuery.getStatement();
        
        String selectUsersStatement = "SELECT * FROM users";
        
        statement.execute(selectUsersStatement);
        
        ResultSet usersSet = statement.getResultSet();
        
        while(usersSet.next()) {
            int userID = usersSet.getInt("User_ID");
            String name = usersSet.getString("User_Name");
            String password = usersSet.getString("Password");
                    
        }
        
        return allUsers;
    }
    
    
}
