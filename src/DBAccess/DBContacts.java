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
import model.Contacts;
import model.Manager;

/**
 *
 * @author tyler
 */
public class DBContacts {
    /**
     * With the use of a prepared statement, load the contacts from the SQL database and
     * iterate over the result set and add them as a contact
     * 
     * @throws SQLException 
     */
    public static void loadContacts() throws SQLException {
        DBQuery.setStatement(DBConnection.getConnection());
        Statement statement = DBQuery.getStatement();
        String selectContacts = "SELECT * FROM contacts";
        
        statement.execute(selectContacts);
        
        ResultSet cSet = statement.getResultSet();
        
        while (cSet.next()) {
            int id = cSet.getInt("Contact_ID");
            String name = cSet.getString("Contact_Name");
            String email = cSet.getString("Email");
            Contacts contact = new Contacts(id, name, email);
            Manager.addContact(contact);
        }
    }
    
}
