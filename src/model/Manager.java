/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    
    
    
    
    
    
    
    //Appointments
    public static void addAppointment (Appointment apt) {
        allAppointments.add(apt);
    }
    
    
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }
    
    public static ObservableList<Appointment> deleteAllAppointments() {
        for (Appointment apt : allAppointments) {
            allAppointments.remove(apt);
        }
        return allAppointments;
    }
    
    
    
    
    //Customers
    public static void addCustomer (Customers cust) {
        allCustomers.add(cust);
    }
    
    public static ObservableList<Customers> getAllCustomers() {
        return allCustomers;
    }
    
    public static ObservableList<Customers> deleteAllCustomers() {
        for (Customers cust : allCustomers) {
            allCustomers.remove(cust);
        }
        return allCustomers;
    }
    
    
    //Divisions
    public static void addDivision (Divisions div) {
        allDivisions.add(div);
    }
    
    public static ObservableList<Divisions> getAllDivisions() {
        return allDivisions;
    }
    
    public static ObservableList<Divisions> deleteAllDivisions() {
        for (Divisions div : allDivisions) {
            allDivisions.remove(div);
        }
        return allDivisions;
    }
}
