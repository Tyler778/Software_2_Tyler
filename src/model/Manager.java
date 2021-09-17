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
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
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
    
    
    //Load all Data
    public static void loadData() throws SQLException {
        DBCustomers.loadCustomers();
        DBAppointments.loadAppointments();
        DBCountries.loadCountries();
        DBDivisions.loadDivisions();
        DBUsers.loadUsers();
        DBContacts.loadContacts();
        
    }
    
    //Delete all Data
    public static void deleteData() throws SQLException {
        deleteAllAppointments();
        deleteAllCustomers();
        deleteAllDivisions();
        deleteAllCountries();
        deleteAllUsers();
        deleteAllContacts();
        
        
    }
    
    
    //Appointments
    public static void addAppointment (Appointment apt) {
        allAppointments.add(apt);
    }
    
    
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }
    
    public static void deleteAllAppointments() {
        ObservableList<Appointment>holdAppt = FXCollections.observableArrayList();
        for (Appointment apt : allAppointments) {
            holdAppt.add(apt);
        }
        allAppointments.removeAll(holdAppt);
    }
    public static void removeAppointment (Appointment apt) throws SQLException {
        allAppointments.remove(apt);
        DBAppointments.deleteAppointment(apt);
    }
    
    
    
    
    //Customers
    public static void addCustomer (Customers cust) {
        allCustomers.add(cust);
    }
    public static void removeCustomer (Customers cust) throws SQLException {
        allCustomers.remove(cust);
        DBCustomers.deleteCustomer(cust);
    }
    
    public static ObservableList<Customers> getAllCustomers() {
        return allCustomers;
    }
    
    public static void deleteAllCustomers() {
        ObservableList<Customers>holdCust = FXCollections.observableArrayList();
        for (Customers cust : allCustomers) {
            holdCust.add(cust);
        }
        allCustomers.removeAll(holdCust);
    }
    
    
    //Divisions
    public static void addDivision (Divisions div) {
        allDivisions.add(div);
    }
    public static void addDivisionName (String name) {
        allDivisionsNames.add(name);
    }
    public static ObservableList<String> getAllDivisionNames() {
        return allDivisionsNames;
    }
    
    public static ObservableList<Divisions> getAllDivisions() {
        return allDivisions;
    }
    
    public static void deleteAllDivisions() {
        ObservableList<Divisions>holdDiv = FXCollections.observableArrayList();
        for (Divisions div : allDivisions) {
            holdDiv.add(div);
        }
        allDivisions.removeAll(holdDiv);
    }
    
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
    
    //Countries
    public static void addCountry (Countries country) {
        allCountries.add(country);
    }
    public static void addCountryName (String name) {
        allCountryNames.add(name);
    }
    public static ObservableList<String> getAllCountryNames() {
        return allCountryNames;
    }
    
    public static void deleteAllCountryNames() {
        ObservableList<String>holdCountryNames = FXCollections.observableArrayList();
        for (String country : allCountryNames) {
            holdCountryNames.add(country);
        }
        allCountryNames.removeAll(holdCountryNames);
    }
    public static ObservableList<Countries> getAllCountries() {
        return allCountries;
    }
    public static void deleteAllCountries() {
        ObservableList<Countries>holdCountry = FXCollections.observableArrayList();
        for (Countries country : allCountries) {
            holdCountry.add(country);
        }
        allCountries.removeAll(holdCountry);
    }
    
    
    
    //Credentials
    
    public static void addUser (Users user) {
        allUsers.add(user);
    }
    
    public static void deleteAllUsers() {
        ObservableList<Users>holdUsers = FXCollections.observableArrayList();
        for (Users user : allUsers) {
            holdUsers.add(user);
        }
        allUsers.removeAll(holdUsers);
    }
    
    public static ObservableList<Users> getAllUsers() {
        return allUsers;
    }
    
    
    
    //Contacts
    
    public static void addContact (Contacts contact) {
        allContacts.add(contact);
    }
    public static void deleteAllContacts() {
        ObservableList<Contacts>holdContacts = FXCollections.observableArrayList();
        for(Contacts contact : allContacts) {
            holdContacts.add(contact);
        }
        allContacts.removeAll(holdContacts);
    }
    
    public static ObservableList<Contacts> getAllContacts() {
        return allContacts;
    }
    
    
}
