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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tyler
 */
public class LoginHomeController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label invalidLabel;
    @FXML
    private AnchorPane timeZoneLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        invalidLabel.setVisible(false);
    }    
    
    
    public boolean validateUser() {
        boolean usernameCheck = false;
        boolean passwordCheck = false;
        boolean valid = false;
        if (usernameTextField.getText().equals("test")) {
            usernameCheck = true;
        }
        else {
            usernameCheck = false;
        }
        if (passwordTextField.getText().equals("test")) {
            passwordCheck = true;
        }
        else {
            passwordCheck = false;
        }
        if (usernameCheck && passwordCheck) {
            valid = true;
        }
        return valid;
    };

    @FXML
    private void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void onActionLogin(ActionEvent event) throws IOException {
        if (validateUser()) {
            //TODO Switch Screens
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/SchedulingHome.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else {
            invalidLabel.setVisible(true);
        }
        
    }
    
}
