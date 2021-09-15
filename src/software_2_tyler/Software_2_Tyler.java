/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_2_tyler;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Utilities.DBConnection;
import Utilities.DBQuery;
//import com.mysql.cj.xdevapi.Statement;
import java.sql.Statement;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

/**
 *
 * @author tyler
 */
public class Software_2_Tyler extends Application{

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        //French?
        //Locale.setDefault(new Locale("fr"));
        
        //Initialize connection with Database
        DBConnection.startConnection();
        
        
        try {
                DBCustomers.loadCustomers();
            } catch (SQLException ex) {
                System.out.println("SQL EXCEPTION");
       
            }  
            try {
                DBAppointments.loadAppointments();
            } catch (SQLException ex) {
                System.out.println("SQL EXCEPTION");
            }
            
            DBDivisions.loadDivisions();
        
        
        
        
        launch(args);
        
        
        //End Connection with Database
        DBConnection.closeConnection();
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginHome.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Worldwide Scheduling for Consultant Company X");
        stage.show();
        stage.setMaximized(true);
    }
    
}
