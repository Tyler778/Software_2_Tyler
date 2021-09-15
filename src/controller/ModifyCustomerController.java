/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customers;
import model.Divisions;
import model.Manager;

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
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        divisionBox.setValue("Division Production");
        divisionBox.setItems(Manager.getAllDivisionNames());
        //divisionBox.setValue("Division Production");
        // TODO
    }    
    
    
    
    
    public void sendCustomer(Customers customer) {
        customerTextField.setText(String.valueOf(customer.getId()));
        addressTextField.setText(String.valueOf(customer.getAddress()));
        nameTextField.setText(String.valueOf(customer.getCustomerName()));
        postalTextField.setText(String.valueOf(customer.getPostalCode()));
        phoneTextField.setText(String.valueOf(customer.getPhone()));
        
    }

    @FXML
    private void onActionSaveCustomer(ActionEvent event) {
    }


    @FXML
    private void onActionCancelModifyingCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SchedulingHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
}
