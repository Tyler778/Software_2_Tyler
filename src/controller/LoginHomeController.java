/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
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
import model.Manager;
import model.Users;

/**
 * FXML Controller class
 *
 * @author tyler
 */
public class LoginHomeController implements Initializable {
    Stage stage;
    Parent scene;
    public static String userLoggedIn = null;
    public static LocalDateTime loginDateTime = null;
    public static Integer userLoggedInID = 0;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label invalidLabel;
    @FXML
    private Label timeZoneLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //French?
        //Locale.setDefault(new Locale("fr"));
        
        // TODO
        invalidLabel.setVisible(false);
        
        timeZoneLabel.setText("Timezone : " + String.valueOf(TimeZone.getDefault().getDisplayName()));
    }    
    
    
    public boolean validateUser() {
        boolean validCredentials = false;
        for(Users user : Manager.getAllUsers()) {
            if(usernameTextField.getText().equals(user.getName()) && passwordTextField.getText().equals(user.getPassword())) {
                validCredentials = true;
                userLoggedIn = user.getName();
                userLoggedInID = user.getId();
                loginDateTime = LocalDateTime.now();
                SchedulingHomeController.alertCheck();
            }
        }
        return validCredentials;
    }

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
