/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Utilities.DBConnection;
import Utilities.DBQuery;
import java.sql.Statement;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author tyler
 */
public class DBAppointments {
    
    
    //init observable list of appointments
    private static ObservableList<Appointment>allAppointments = FXCollections.observableArrayList();
    
    
    
    public static ObservableList<Appointment> loadAppointments () throws SQLException {
        DBConnection.getConnection();
        DBQuery.setStatement(DBConnection.getConnection());
        Statement statement = DBQuery.getStatement();
        
        
        String selectApptStatement = "SELECT * FROM appointments";
        
        statement.execute(selectApptStatement);
        ResultSet apptSet = statement.getResultSet();
        
        while(apptSet.next()) {
            int apptID = apptSet.getInt("Appointment_ID");
            String title = apptSet.getString("Title");
            String desc = apptSet.getString("Description");
            String location = apptSet.getString("Location");
            String type = apptSet.getString("Type");
            LocalDate startDate = apptSet.getDate("Start").toLocalDate();
            LocalTime startTime = apptSet.getTime("Start").toLocalTime();
            LocalDate endDate = apptSet.getDate("End").toLocalDate();
            LocalTime endTime = apptSet.getTime("End").toLocalTime();
            LocalDate createDate = apptSet.getDate("Create_Date").toLocalDate();
            LocalTime createTime = apptSet.getTime("Create_Date").toLocalTime();
            String createdBy = apptSet.getString("Created_By");
            LocalDateTime lastUpdate = apptSet.getTimestamp("Last_Update").toLocalDateTime();
            String updatedBy = apptSet.getString("Last_Updated_By");
            int customerID = apptSet.getInt("Customer_ID");
            int userID = apptSet.getInt("User_ID");
            int contactID = apptSet.getInt("Contact_ID");
            Appointment apt1 = new Appointment(apptID, title, desc, location, type, startDate, startTime, endDate, endTime, createDate, createTime, createdBy, lastUpdate, updatedBy, customerID, userID, contactID);
            allAppointments.add(apt1);
        }
        
        return allAppointments;
        
    }
    
    
}
