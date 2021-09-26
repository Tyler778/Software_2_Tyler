/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utilities.DBConnection;
import Utilities.DBQuery;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customers;
import model.Divisions;
import model.Manager;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 * FXML Controller class
 *
 * @author tyler
 */
public class ModifyCustomerController implements Initializable {
    
    
    Stage stage;
    Parent scene;

    @FXML
    private TextField customerTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField postalTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private ChoiceBox<String> divisionBox;
    @FXML
    private ChoiceBox<String> countryBox;
    @FXML
    private Label appointmentTotal;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    
    /**
     * Receives the customer object and sets the fields and combo boxes the appropriate values retrieved from their getters.
     * @param customer
     * @throws SQLException 
     */
    public void sendCustomer(Customers customer) throws SQLException {
        customerTextField.setText(String.valueOf(customer.getId()));
        addressTextField.setText(String.valueOf(customer.getAddress()));
        nameTextField.setText(String.valueOf(customer.getCustomerName()));
        postalTextField.setText(String.valueOf(customer.getPostalCode()));
        phoneTextField.setText(String.valueOf(customer.getPhone()));
        countryBox.setItems(Manager.getAllCountryNames());
        countryBox.setValue(String.valueOf(customer.getCountry()));
        
        divisionBox.setItems(Manager.getDivisionsBasedOnCountry(customer.getCountry()));
        divisionBox.setValue(String.valueOf(customer.getDivName()));
        appointmentTotal.setText(appointmentCount(customer));
        
        
    }
    /**
     * Returns a string that is a count of all Appointments under a specific customer ID.  Uses a SQL statement that connects to the server to obtain.
     * @param customer
     * @return
     * @throws SQLException 
     */
    private String appointmentCount(Customers customer) throws SQLException {
        
        DBQuery.setStatement(DBConnection.getConnection());
        Statement statement = DBQuery.getStatement();
        String countAppt = "SELECT COUNT(Appointment_ID) FROM appointments WHERE Customer_ID = '" + customer.getId() + "'";
        statement.execute(countAppt);
        ResultSet cAppt = statement.getResultSet();
        String appointmentTotal = null;
        while (cAppt.next()) {
            String count = String.valueOf(cAppt.getInt("COUNT(Appointment_ID)"));
            appointmentTotal = count;
        }
        return appointmentTotal;
        
    }
    /**
     * Save the Customer with the use of a statement object and inserts the correct values into a string to send to the SQL server to update the object in the database.  Then, if successful, sets the stage to the Scheduling Home FXML.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void onActionSaveCustomer(ActionEvent event) throws IOException {
        
        String divID = null;
        
        for(Divisions div : Manager.getAllDivisions()) {
            if(divisionBox.getValue().equals(div.getName())) {
                divID = String.valueOf(div.getId());
            }
        }
        
        try{
            DBQuery.setStatement(DBConnection.getConnection());
            Statement statement = DBQuery.getStatement();
        
            String updateStatement = "UPDATE customers SET "
               + "Customer_Name = '" + nameTextField.getText()
                + "', Address = '" + addressTextField.getText()
                + "', Postal_Code = '" + postalTextField.getText()
                + "', Phone = '" + phoneTextField.getText()
                + "', Last_Update = '" + String.valueOf(LocalDateTime.now())
                + "', Last_Updated_By = 'test"
                + "', Division_ID = '" + divID
                + "' WHERE Customer_ID = '" + customerTextField.getText() + "'";
            statement.execute(updateStatement);
        } catch(SQLException e) {
            System.out.println(e);
        }
        SchedulingHomeController.reloadData = true;
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SchedulingHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
 
    }

    /**
     * Sets the stage to the Scheduling Home FXML without saving any of the modifications.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void onActionCancelModifyingCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SchedulingHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Sets the division box on a list of divisions that have a foreign key of the specified country from country combo box.
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void comboAction(ActionEvent event) throws SQLException {
        divisionBox.setItems(Manager.getDivisionsBasedOnCountry(countryBox.getValue()));
        
    }

}
