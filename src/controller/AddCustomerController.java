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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customers;
import java.sql.Statement;
import java.time.LocalDateTime;
import model.Divisions;
import model.Manager;

/**
 * FXML Controller class
 *
 * @author tyler
 */
public class AddCustomerController implements Initializable {
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        countryBox.setItems(Manager.getAllCountryNames());
        // TODOf
    }    

    @FXML
    private void comboAction(ActionEvent event) throws SQLException {
        divisionBox.setItems(Manager.getDivisionsBasedOnCountry(countryBox.getValue()));
        
    }

    @FXML
    private void onActionAddCustomer(ActionEvent event) throws SQLException, IOException {
        String timeStamp = String.valueOf(LocalDateTime.now());
        
        String divID = null;
        for(Divisions div : Manager.getAllDivisions()) {
            if(divisionBox.getValue().equals(div.getName())) {
                divID = String.valueOf(div.getId());
            }
        }
        
        
        DBQuery.setStatement(DBConnection.getConnection());
        Statement statement = DBQuery.getStatement();
        String addStatement = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID)"
                + " VALUES ('"
                + nameTextField.getText() + "', '"
                + addressTextField.getText() + "', '"
                + postalTextField.getText() + "', '"
                + phoneTextField.getText() + "', '"
                + timeStamp + "', '"
                + "test', '"
                + timeStamp + "', '"
                + "test', '" 
                + divID + "');";
        statement.execute(addStatement);
        SchedulingHomeController.reloadData = true;
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SchedulingHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
              
    }

    @FXML
    private void onActionCancelAddingCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SchedulingHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    
    
    
    
}
