/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCountries;
import DBAccess.DBUsers;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Utilities.DBConnection;
import Utilities.DBQuery;
import controller.LoginHomeController;
import controller.ModifyAppointmentController;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author tyler
 */
public class Manager {
    
    
    
    private static ObservableList<Customers>allCustomers = FXCollections.observableArrayList();
    private static ObservableList<Appointment>allAppointments = FXCollections.observableArrayList();
    private static ObservableList<Divisions>allDivisions = FXCollections.observableArrayList();
    private static ObservableList<String>allDivisionsNames = FXCollections.observableArrayList();
    private static ObservableList<Countries>allCountries = FXCollections.observableArrayList();
    private static ObservableList<String>allCountryNames = FXCollections.observableArrayList();
    private static ObservableList<Users>allUsers = FXCollections.observableArrayList();
    private static ObservableList<Contacts>allContacts = FXCollections.observableArrayList();
    private static ObservableList<String>allContactNames = FXCollections.observableArrayList();
    private static ObservableList<Integer>allCustomerIDs = FXCollections.observableArrayList();
    private static ObservableList<Integer> allUserIDs = FXCollections.observableArrayList();
    
    
    /**
     * Calls appropriate Database Access classes, populating the classes with instances of objects from the result sets that are returned.
     * @throws SQLException 
     */
    public static void loadData() throws SQLException {
        DBCustomers.loadCustomers();
        DBAppointments.loadAppointments();
        DBCountries.loadCountries();
        DBDivisions.loadDivisions();
        DBUsers.loadUsers();
        DBContacts.loadContacts();
        
    }
    
    /**
     * Calls appropriate methods in Manager that will remove all instances of objects.  Typically done right before updating the objects with data from the current connection to the SQL server.
     * @throws SQLException 
     */
    public static void deleteData() throws SQLException {
        deleteAllAppointments();
        deleteAllCustomers();
        deleteAllDivisions();
        deleteAllCountries();
        deleteAllUsers();
        deleteAllContacts();
        deleteAllContactNames();
        deleteAllDivisionNames();
        
        
    }
    
    
    /**
     * Adds the Appointment argument to the allAppointments Observable List.
     * @param appt 
     */
    public static void addAppointmentNODB(Appointment appt) {
        allAppointments.add(appt);
    }
    /**
     * Sends the arguments accepted into the DBAppointments to add the fields into the SQL Appointments Table.
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
    public static void addAppointment (String title, String desc, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createBy, Timestamp lastUpdate, String updatedBy, Integer customerID, Integer userID, Integer contactID) throws SQLException {
        DBAppointments.addAppointment(title, desc, location, type, start, end, createDate, createBy, lastUpdate, updatedBy, customerID, userID, contactID);
    }
    
    /**
     * Returns allAppointments Observable List.
     * @return 
     */
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }
    /**
     * Uses Lambda expression to delete all Appointments from the allAppointments ObservableList and the lambda expressions avoids a ConcurrentModificationException because it creates and populates a seperate Observable List that can be used to delete all from at once.
     */
    public static void deleteAllAppointments() {
        ObservableList<Appointment>holdAppt = FXCollections.observableArrayList();
        allAppointments.forEach((x) -> {
            holdAppt.add(x);
        });
        allAppointments.removeAll(holdAppt);
    }
    /**
     * Removes the Appointment argument from the allAppointments Observable List and sends the Appointment to the DBAppointments to delete it from the SQL server.
     * @param apt
     * @throws SQLException 
     */
    public static void removeAppointment (Appointment apt) throws SQLException {
        allAppointments.remove(apt);
        DBAppointments.deleteAppointment(apt);
    }
    /**
     * Sends the accepted arguments to the DBAppointments class to use in a prepared statement and update the SQL instance.
     * @param id
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
    public static void updateAppointment (Integer id, String title, String desc, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createBy, Timestamp lastUpdate, String updatedBy, Integer customerID, Integer userID, Integer contactID) throws SQLException {
        DBAppointments.updateAppointment(id, title, desc, location, type, start, end, createDate, createBy, lastUpdate, updatedBy, customerID, userID, contactID);
    }
    /**
     * Called on log in of the user, checks the allAppointments Observable List of appointments that have start times within 15 minutes of login.  Returns a Boolean.
     * @return
     */
    public static boolean checkAppointmentProximity() {
        boolean nearAppointment = false;
        
        for(Appointment appt : allAppointments) {
            if(appt.getUserID() == LoginHomeController.userLoggedInID && LoginHomeController.loginDateTime.plusMinutes(15).isAfter(appt.getStartDateTime()) && LoginHomeController.loginDateTime.isBefore(appt.getStartDateTime())) {
                LoginHomeController.apptMatchID = appt.getId();
                LoginHomeController.apptMatchStart = appt.getStartDateTime();
                nearAppointment = true;
                return nearAppointment;
            }
        }
        return nearAppointment;
        
    }
    /**
     * Returns a list of Appointments that have a specific Contact Name.
     * @param contactName
     * @return
     */
    public static ObservableList<Appointment> getFilteredAppointments(String contactName) {
        
        ObservableList<Appointment>holdAppt = FXCollections.observableArrayList();
        holdAppt.clear();
        for(Appointment appt : Manager.getAllAppointments()) {
            if(appt.getContactName().equals(contactName)) {
                holdAppt.add(appt);
            }
        }
        return holdAppt;
        
    }
    /**
     * Returns a list of Appointments that are associated with the user specified in the argument.
     * @param user
     * @return
     */
    public static ObservableList<Appointment> filterAppointmentsByUser(String user) {
        ObservableList<Appointment>holdAppt = FXCollections.observableArrayList();
        holdAppt.clear();
        for(Appointment appt : Manager.getAllAppointments()) {
            if(appt.getUserID() == getIDFromUserName(user)) {
                holdAppt.add(appt);
            }
        }
        return holdAppt;
        
        
        
    }
    
    
    
    
    
    /**
     * Add a Customer to the allCustomers Observable List
     * @param cust 
     */
    public static void addCustomer (Customers cust) {
        allCustomers.add(cust);
    }
    /**
     * Remove a Customer from the allCustomers Observable List and calls the DBCustomers method to delete the argument from the SQL server.
     * @param cust
     * @throws SQLException 
     */
    public static void removeCustomer (Customers cust) throws SQLException {
        allCustomers.remove(cust);
        DBCustomers.deleteCustomer(cust);
    }
    /**
     * Returns the allCustomers Observable List
     * @return
     */
    public static ObservableList<Customers> getAllCustomers() {
        return allCustomers;
    }
    /**
     * Removes all objects from the allCustomers Observable List
     */
    public static void deleteAllCustomers() {
        ObservableList<Customers>holdCust = FXCollections.observableArrayList();
        for (Customers cust : allCustomers) {
            holdCust.add(cust);
        }
        allCustomers.removeAll(holdCust);
    }
    /**
     * Returns Observable List of all Customer IDs
     * @return
     */
    public static ObservableList<Integer> getAllCustomerIDs() {
        ObservableList<Integer>holdCustID = FXCollections.observableArrayList();
        for (Customers cust : allCustomers) {
            holdCustID.add(cust.getId());
        }
        allCustomerIDs.removeAll(holdCustID);
        
        for (Customers cust : allCustomers) {
            allCustomerIDs.add(cust.getId());
        }
        return allCustomerIDs;
    }
    /**
     * Returns Observable List of all Customer Names in the form of strings
     * @return
     */
    public static ObservableList<String> getAllCustomerNames() {
        ObservableList<String>holdCustName = FXCollections.observableArrayList();
        for(Customers cust : allCustomers) {
            holdCustName.add(cust.getCustomerName());
        }
        return holdCustName;
    }
    
    /**
     * Returns a Boolean to check if the argument of Customer_ID has no appointments scheduled.
     * @param id
     * @return
     */
    public static boolean checkAppointments(int id) {
        boolean remainingApts = false;
        for(Appointment appt : allAppointments) {
            if(appt.getCustomerID() == id) {
                remainingApts = true;
                return remainingApts;
            }
        }
        return remainingApts;
    }
    /**
     * Return the ID of the customer with the name sent in as an argument
     * @param name
     * @return
     */
    public static Integer getSpecificCustomerID(String name) {
        int x = 0;
        for(Customers cust : Manager.getAllCustomers()) {
            if(cust.getCustomerName().equals(name)) {
                x = cust.getId();
            }
        }
        return x;
    }
    
    
    /**
     * Add a Division to the allDivisions Observable List
     * @param div 
     */
    public static void addDivision (Divisions div) {
        allDivisions.add(div);
    }
    /**
     * Add a Division Name to the allDivisionsNames Observable List
     * @param name 
     */
    public static void addDivisionName (String name) {
        allDivisionsNames.add(name);
    }
    /**
     * Returns allDivisionNames Observable List
     * @return
     */
    public static ObservableList<String> getAllDivisionNames() {
        return allDivisionsNames;
    }
    /**
     * Returns allDivisions Observable List
     * @return
     */
    public static ObservableList<Divisions> getAllDivisions() {
        return allDivisions;
    }
    /**
     * Removes every object from the allDivisions Observable List
     */
    public static void deleteAllDivisions() {
        ObservableList<Divisions>holdDiv = FXCollections.observableArrayList();
        for (Divisions div : allDivisions) {
            holdDiv.add(div);
        }
        allDivisions.removeAll(holdDiv);
    }
    /**
     * Removes every String from the allDivisionsNames Observable List
     */
    public static void deleteAllDivisionNames() {
        ObservableList<String>holdDivisionNames = FXCollections.observableArrayList();
        for (String division : allDivisionsNames) {
            holdDivisionNames.add(division);
        }
        allDivisionsNames.removeAll(holdDivisionNames);
    }
    /**
     * On Argument of the Country Name, select all divisions under the country with a SQL statement.  
     * @param countryName
     * @return
     * @throws SQLException 
     */
    public static ObservableList<String> getDivisionsBasedOnCountry(String countryName) throws SQLException {
        DBQuery.setStatement(DBConnection.getConnection());
        Statement statement = DBQuery.getStatement();
        ObservableList<String>currentDivisions = FXCollections.observableArrayList();
        int countryID = 0;
        for (Countries country : Manager.getAllCountries()) {
            if(countryName.equals(country.getName())) {
                countryID = country.getId();
            }
        }
        String selectDivisionsBasedOnCountry = "SELECT Division FROM first_level_divisions WHERE COUNTRY_ID = '" + countryID + "'";
        
        statement.execute(selectDivisionsBasedOnCountry);
        ResultSet divSet = statement.getResultSet();
        
        while (divSet.next()) {
            String divName = divSet.getString("Division");
            currentDivisions.add(divName);
        }
        return currentDivisions;
    }
    
    /**
     * Add a country to the allCountries Observable List
     * @param country 
     */
    public static void addCountry (Countries country) {
        allCountries.add(country);
    }
    /**
     * Add a country name to the allCountryNames Observable List
     * @param name 
     */
    public static void addCountryName (String name) {
        allCountryNames.add(name);
    }
    /**
     * Return the Observable List of allCountryNames
     * @return
     */
    public static ObservableList<String> getAllCountryNames() {
        return allCountryNames;
    }
    /**
     * Remove all from the allCountryNames Observable List
     */
    public static void deleteAllCountryNames() {
        ObservableList<String>holdCountryNames = FXCollections.observableArrayList();
        for (String country : allCountryNames) {
            holdCountryNames.add(country);
        }
        allCountryNames.removeAll(holdCountryNames);
    }
    /**
     * Returns allCountries Observable List
     * @return
     */
    public static ObservableList<Countries> getAllCountries() {
        return allCountries;
    }
    /**
     * Remove all objects from the allCountries Observable List
     */
    public static void deleteAllCountries() {
        ObservableList<Countries>holdCountry = FXCollections.observableArrayList();
        for (Countries country : allCountries) {
            holdCountry.add(country);
        }
        allCountries.removeAll(holdCountry);
    }
    
    
    
    
    /**
     * Add user to the allUsers Observable List
     * @param user 
     */
    public static void addUser (Users user) {
        allUsers.add(user);
    }
    /**
     * Remove all Users from the allUsers Observable List
     */
    public static void deleteAllUsers() {
        ObservableList<Users>holdUsers = FXCollections.observableArrayList();
        for (Users user : allUsers) {
            holdUsers.add(user);
        }
        allUsers.removeAll(holdUsers);
    }
    /**
     * Returns allUsers Observable List
     * @return
     */
    public static ObservableList<Users> getAllUsers() {
        return allUsers;
    }
    /**
     * Returns an Observable List of Integers of allUserIDs
     * @return
     */
    public static ObservableList<Integer> getAllUserIDs() {
        ObservableList<Integer>holdUserIDs = FXCollections.observableArrayList();
        for (Users user : allUsers) {
            holdUserIDs.add(user.getId());
        }
        allUserIDs.removeAll(holdUserIDs);
        
        for (Users user : allUsers) {
            allUserIDs.add(user.getId());
        }
        return allUserIDs;
    }
    /**
     * Return an Observable List of Strings that are all Users names
     * @return
     */
    public static ObservableList<String> getAllUserNames() {
        ObservableList<String>holdUsers = FXCollections.observableArrayList();
        for(Users user : getAllUsers()) {
            holdUsers.add(user.getName());
        }
        return holdUsers;
    }
    /**
     * Returns a specific ID given the argument of the username.  Used in the login.
     * @param username
     * @return
     */
    public static Integer getIDFromUserName(String username) {
        for(Users user : getAllUsers()) {
            if(user.getName().equals(username)) {
                return user.getId();
            }
        }
        return null;
    }
    
    
    
    /**
     * Add a contact to the allContacts Observable List
     * @param contact 
     */
    public static void addContact (Contacts contact) {
        allContacts.add(contact);
    }
    /**
     * Remove every contact from the allContacts Observable List
     */
    public static void deleteAllContacts() {
        ObservableList<Contacts>holdContacts = FXCollections.observableArrayList();
        for(Contacts contact : allContacts) {
            holdContacts.add(contact);
        }
        allContacts.removeAll(holdContacts);
    }
    /**
     * Returns allContacts Observable List
     * @return
     */
    public static ObservableList<Contacts> getAllContacts() {
        return allContacts;
    }
    /**
     * Returns Observable List of allContactNames
     * @return
     */
    public static ObservableList<String> getAllContactNames() {
        ObservableList<String>holdContactName = FXCollections.observableArrayList();
        for (String contact : allContactNames) {
            holdContactName.add(contact);
        }
        allContactNames.removeAll(holdContactName);
        for(Contacts contact : Manager.getAllContacts()) {
            allContactNames.add(contact.getName());
        }
        return allContactNames;
    }
    /**
     * Removes every String from allContactNames Observable List
     */
     public static void deleteAllContactNames() {
        ObservableList<String>holdContactName = FXCollections.observableArrayList();
        for (String contact : allContactNames) {
            holdContactName.add(contact);
        }
        allContactNames.removeAll(holdContactName);
    }
    
    
}
