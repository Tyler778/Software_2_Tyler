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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
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
        //divisionBox.setValue("Ohio");
        //divisionBox.setValue("Division Production");
        // TODO
    }    
    
    
    
    
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
            System.out.println(updateStatement);
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


    @FXML
    private void onActionCancelModifyingCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SchedulingHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    
    @FXML
    private void comboAction(ActionEvent event) throws SQLException {
        divisionBox.setItems(Manager.getDivisionsBasedOnCountry(countryBox.getValue()));
        
    }

}
