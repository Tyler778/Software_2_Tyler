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
    
    
    
    
    /**
     * With connection from DBConnection, send a prepared statement to the database
     * and retrieve all appointments with foreign keys.  Also iterate over result set and load 
     * objects into Appointment
     * @throws SQLException 
     */
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
            
            //LocalDate startDate = apptSet.getDate("Start").toLocalDate();
            //LocalTime startTime = apptSet.getTime("Start").toLocalTime();
            LocalDateTime startDateTime = apptSet.getTimestamp("Start").toLocalDateTime();
            
            //LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
            
            //LocalDate endDate = apptSet.getDate("End").toLocalDate();
            //LocalTime endTime = apptSet.getTime("End").toLocalTime();
            LocalDateTime endDateTime = apptSet.getTimestamp("End").toLocalDateTime();
            
            //LocalDate createDate = apptSet.getDate("Create_Date").toLocalDate();
            //LocalTime createTime = apptSet.getTime("Create_Date").toLocalTime();
            LocalDateTime createDateTime = apptSet.getTimestamp("Create_Date").toLocalDateTime();
            
            
            String createdBy = apptSet.getString("Created_By");
            LocalDateTime lastUpdate = apptSet.getTimestamp("Last_Update").toLocalDateTime();
            
            
            String updatedBy = apptSet.getString("Last_Updated_By");
            int customerID = apptSet.getInt("Customer_ID");
            int userID = apptSet.getInt("User_ID");
            int contactID = apptSet.getInt("Contact_ID");
            String contactName = apptSet.getString("Contact_Name");
            Appointment apt1 = new Appointment(apptID, title, desc, location, type, startDateTime, endDateTime, createDateTime, createdBy, lastUpdate, updatedBy, customerID, userID, contactID, contactName);
            
            Manager.addAppointmentNODB(apt1);
            
            
            
        }
                
    }
    /**
     * With input Appointment, delete the appointment from the SQL database with a prepared statement
     * @param apt   the appointment to delete
     * @throws SQLException 
     */
    public static void deleteAppointment(Appointment apt) throws SQLException {
        String deleteAppointment = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement deleteAppointmentPS = DBConnection.getConnection().prepareStatement(deleteAppointment);
        deleteAppointmentPS.setInt(1, apt.getId());
        
        deleteAppointmentPS.executeUpdate();
    }
    /**
     * With given values to update, insert values into a prepared statement and update the SQL database with it
     * 
     * 
     * @param id    the id of the appointment to update
     * @param title
     * @param desc
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createDate
     * @param createBy
     * @param lastUpdate
     * @param updatedBy
     * @param customerID
     * @param userID
     * @param contactID
     * @throws SQLException 
     */
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
        updateStatementPS.setInt(11, customerID);
        updateStatementPS.setInt(12, userID);
        updateStatementPS.setInt(13, contactID);
        updateStatementPS.setInt(14, id);
        
        
        updateStatementPS.executeUpdate();
       
    }
    /**
     * With given values, add an appointment to the SQL database with the use of a prepared statement
     * 
     * 
     * @param title
     * @param desc
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createDate
     * @param createBy
     * @param lastUpdate
     * @param updatedBy
     * @param customerID
     * @param userID
     * @param contactID
     * @throws SQLException 
     */
    public static void addAppointment(String title, String desc, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createBy, Timestamp lastUpdate, String updatedBy, Integer customerID, Integer userID, Integer contactID) throws SQLException {

        String addStatement = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement addStatementPS = DBConnection.getConnection().prepareStatement(addStatement);
        
        addStatementPS.setString(1, title);
        addStatementPS.setString(2, desc);
        addStatementPS.setString(3, location);
        addStatementPS.setString(4, type);
        addStatementPS.setTimestamp(5, Timestamp.valueOf(start));
        addStatementPS.setTimestamp(6, Timestamp.valueOf(end));
        addStatementPS.setTimestamp(7, Timestamp.valueOf(createDate));
        addStatementPS.setString(8, createBy);
        addStatementPS.setTimestamp(9, lastUpdate);
        addStatementPS.setString(10, updatedBy);
        addStatementPS.setInt(11, customerID);
        addStatementPS.setInt(12, userID);
        addStatementPS.setInt(13, contactID);
        
        System.out.println(addStatementPS);
        
        addStatementPS.executeUpdate();
    }
    
    
    
    
}
