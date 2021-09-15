/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Utilities.DBConnection;
import Utilities.DBQuery;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import model.Manager;

/**
 *
 * @author tyler
 */
public class DBCustomers {
    
    
    
    
    public static void loadCustomers () throws SQLException {
        Manager.deleteAllCustomers();
        DBQuery.setStatement(DBConnection.getConnection());
        Statement statement = DBQuery.getStatement();
        
        String selectCustomersStatement = "SELECT * FROM WJ07nX6.CustomersTablewithDivisionName;";
        
        statement.execute(selectCustomersStatement);
        ResultSet cSet = statement.getResultSet();
        
        while(cSet.next()) {
            int customerID = cSet.getInt("Customer_ID");
            String customerName = cSet.getString("Customer_Name");
            String customerAddress = cSet.getString("Address");
            String postal = cSet.getString("Postal_Code");
            String phone = cSet.getString("Phone");
            
            LocalDate createDate = cSet.getDate("Create_Date").toLocalDate();
            LocalTime createTime = cSet.getTime("Create_Date").toLocalTime();
            LocalDateTime createDateTime = LocalDateTime.of(createDate, createTime);
            
            String createdBy = cSet.getString("Created_By");
            LocalDateTime lastUpdate = cSet.getTimestamp("Last_Update").toLocalDateTime();
            String updatedBy = cSet.getString("Last_Updated_By");
            int divisionID = cSet.getInt("Division_ID");
            String divName = cSet.getString("Division");
            
            Customers cust1 = new Customers(customerID, customerName, customerAddress, postal, phone, createDateTime, createdBy, lastUpdate, updatedBy, divisionID, divName);
            Manager.addCustomer(cust1);
        }
    }
}
