/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
    public static Integer apptMatchID = 0;
    public static LocalDateTime apptMatchStart;
    public static String defaultTimezone = TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
            
            
            
            //TimeZone.getDefault().getDisplayName();

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label invalidLabel;
    @FXML
    private Label timeZoneLabel;
    @FXML
    private Label loginLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button exitLabel;

    /**
     * Initializes the controller class.  Handles the change to french if it is recognizes as the system default.  Also loads up the timezone currently used.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(Locale.getDefault().getLanguage().equals("fr")) {
           loginLabel.setText("Veuillez vous connecter");
           invalidLabel.setText("Identifiant invalide");
           usernameLabel.setText("Nom d'utilisateur");
           passwordLabel.setText("Le mot de passe");
           loginButton.setText("Connexion");
           exitLabel.setText("Sortir");
           
            
        } else {
            System.out.println("Else ran");
        }
        
        
        
        //French?
        //Locale.setDefault(new Locale("fr"));
        
        // TODO
        invalidLabel.setVisible(false);
        
        timeZoneLabel.setText(defaultTimezone);
    }    
    
    /**
     * Collects the values inputted into the user and password textfield and attempts to match them to a User from the database.  Calls the alertCheck to check if an appointment starts within 15 minutes or not.
     * @return Boolean
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException 
     */
    public boolean validateUser() throws FileNotFoundException, UnsupportedEncodingException {
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
        logActivity(usernameTextField.getText(), validCredentials);
        return validCredentials;
    }
    /**
     * Exits the application.
     * @param event 
     */
    @FXML
    private void onActionExit(ActionEvent event) {
        System.exit(0);
    }
    /**
     * Calls the validateUser method and sets the stage to Scheduling Home FXML if it validates as true.  Otherwise it sets the error message to visible.
     * @param event
     * @throws IOException 
     */
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
    /**
     * Uses the FileWriter called on each press of the login button to log activity to the login_activity.txt file located withinh the root.
     * @param username
     * @param valid
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException 
     */
    public void logActivity(String username, Boolean valid) throws FileNotFoundException, UnsupportedEncodingException {
        try (FileWriter writer = new FileWriter("login_activity.txt", true)) {
            writer.write("User: " + username + " attempted login at " + String.valueOf(LocalDateTime.now()) + ". Validated: " + String.valueOf(valid) + "\n");
        } catch(Exception e) {
            
        }
    }
    
    
    
    
}
