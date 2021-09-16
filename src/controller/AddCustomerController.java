/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
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
        // TODO
    }    

    @FXML
    private void comboAction(ActionEvent event) {
        
    }

    @FXML
    private void onActionAddCustomer(ActionEvent event) {
    }

    @FXML
    private void onActionCancelAddingCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SchedulingHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    
    
    
    
}
