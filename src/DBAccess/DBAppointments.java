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
import model.Appointment;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import model.Manager;

/**
 *
 * @author tyler
 */
public class DBAppointments {
    
    
    
    
    
    public static void loadAppointments () throws SQLException {
        Manager.deleteAllAppointments();
        DBQuery.setStatement(DBConnection.getConnection());
        Statement statement = DBQuery.getStatement();
        
        
        String selectApptStatement = "SELECT * FROM AppointmentTable;";
        
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
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
            
            LocalDate endDate = apptSet.getDate("End").toLocalDate();
            LocalTime endTime = apptSet.getTime("End").toLocalTime();
            LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
            
            LocalDate createDate = apptSet.getDate("Create_Date").toLocalDate();
            LocalTime createTime = apptSet.getTime("Create_Date").toLocalTime();
            LocalDateTime createDateTime = LocalDateTime.of(createDate, createTime);
            
            
            String createdBy = apptSet.getString("Created_By");
            LocalDateTime lastUpdate = apptSet.getTimestamp("Last_Update").toLocalDateTime();
            
            
            String updatedBy = apptSet.getString("Last_Updated_By");
            int customerID = apptSet.getInt("Customer_ID");
            int userID = apptSet.getInt("User_ID");
            int contactID = apptSet.getInt("Contact_ID");
            String contactName = apptSet.getString("Contact_Name");
            Appointment apt1 = new Appointment(apptID, title, desc, location, type, startDateTime, endDateTime, createDateTime, createdBy, lastUpdate, updatedBy, customerID, userID, contactID, contactName);
            
            Manager.addAppointment(apt1);
            
            
            
        }
                
    }
    
    
}
