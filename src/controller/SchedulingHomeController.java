/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Utilities.DBConnection;
import Utilities.DBQuery;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customers;
import model.Manager;
import java.sql.Statement;

/**
 * FXML Controller class
 *
 * @author tyler
 */
public class SchedulingHomeController implements Initializable {
    Stage stage;
    Parent scene;
    
    public static Boolean reloadData = false;
    

    
    @FXML
    private TableView<Appointment> tableAppointments;
    @FXML
    private TableColumn<Appointment, Integer> apptIDCol;
    
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, String> contactCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> startCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> endCol;
    @FXML
    private TableColumn<Appointment, Integer> customerCol;
    @FXML
    private TableView<Customers> tableCustomers;
    @FXML
    private TableColumn<Customers, Integer> customerIDCol;
    @FXML
    private TableColumn<Customers, String> nameCol;
    @FXML
    private TableColumn<Customers, String> addressCol;
    @FXML
    private TableColumn<Customers, String> postalCol;
    @FXML
    private TableColumn<Customers, String> phoneCol;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(reloadData) {
            try {
                reloadData();
            } catch (SQLException ex) {
                Logger.getLogger(SchedulingHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        dataIntoTable();

    }    

    @FXML
    private void onActionAddAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionModifyAppointment(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyAppointment.fxml"));
            loader.load();
            ModifyAppointmentController ModApptController = loader.getController();
            ModApptController.sendAppointment(tableAppointments.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("Select an Appointment");
        }
    }

    @FXML
    private void onActionDeleteAppointment(ActionEvent event) throws SQLException {
        Manager.removeAppointment(tableAppointments.getSelectionModel().getSelectedItem());
        
    }

    @FXML
    private void onActionDeleteCustomer(ActionEvent event) throws SQLException {
        Manager.removeCustomer(tableCustomers.getSelectionModel().getSelectedItem());

        
    }

    @FXML
    private void onActionModifyCustomer(ActionEvent event) throws IOException {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyCustomer.fxml"));
            loader.load();
            ModifyCustomerController ModCusController = loader.getController();
            ModCusController.sendCustomer(tableCustomers.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(Exception e) {
            System.out.println("Select a Customer");
        }
    }

    @FXML
    private void onActionAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    

    
    private void reloadData() throws SQLException {
        System.out.println("Reload Data has attempted to run");
        Manager.deleteData();
        Manager.loadData();
    }

    @FXML
    private void onActionExit(ActionEvent event) {
        System.exit(0);
    }
    
    private void dataIntoTable() {
        tableAppointments.setItems(Manager.getAllAppointments());
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        
        tableCustomers.setItems(Manager.getAllCustomers());
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }
    
}
