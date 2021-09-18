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
import java.sql.PreparedStatement;
import java.sql.Timestamp;

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
    
    public static void deleteAppointment(Appointment apt) throws SQLException {
        String deleteAppointment = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement deleteAppointmentPS = DBConnection.getConnection().prepareStatement(deleteAppointment);
        deleteAppointmentPS.setInt(1, apt.getId());
        
        deleteAppointmentPS.executeUpdate();
    }
    
    public static void updateAppointment(Integer id, String title, String desc, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createBy, Timestamp lastUpdate, String updatedBy, Integer customerID, Integer userID, Integer contactID) throws SQLException {
        
        String updateStatement = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?;";
        PreparedStatement updateStatementPS = DBConnection.getConnection().prepareStatement(updateStatement);

        updateStatementPS.setString(1, title);
        updateStatementPS.setString(2, desc);
        updateStatementPS.setString(3, location);
        updateStatementPS.setString(4, type);
        updateStatementPS.setTimestamp(5, Timestamp.valueOf(start));
        updateStatementPS.setTimestamp(6, Timestamp.valueOf(end));
        updateStatementPS.setTimestamp(7, Timestamp.valueOf(createDate));
        updateStatementPS.setString(8, createBy);
        updateStatementPS.setTimestamp(9, lastUpdate);
        updateStatementPS.setString(10, updatedBy);
        updateStatementPS.setInt(12, customerID);
        updateStatementPS.setInt(13, userID);
        updateStatementPS.setInt(14, contactID);
        updateStatementPS.setInt(15, id);
        
        updateStatementPS.executeUpdate();
       
    }
    
    public static void addAppointment(String title, String desc, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createBy, Timestamp lastUpdate, String updateBy, Integer customerID, Integer userID, Integer contactID) throws SQLException {

        
        
        //String addAppointment = "INSERT INTO appointments"
    }
    
    
    
    
}
