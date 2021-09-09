/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_2_tyler;

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

/**
 *
 * @author tyler
 */
public class Software_2_Tyler extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        //Initialize connection with Database
        DBConnection.startConnection();
        DBQuery.setStatement(DBConnection.getConnection()); //Create Statement Object
        Statement statement = (Statement) DBQuery.getStatement(); //Set Statement reference
        
        //Raw SQL INSERT Statement
        
        String insertStatement = "INSERT INTO countries(country, create_Date, created_By, last_Update, last_Updated_By) VALUES('US', '2021-09-07 00:00:00', 'admin', '2021-09-07 00:00:00', 'admin')";
        
        
        //Execute SQL statement
        //statement.execute(insertStatement);
        
        //Confirm rows affected
        if(statement.getUpdateCount() > 0) {
            System.out.println(statement.getUpdateCount() + " Rows affected");
        } else {
            System.out.println("No Rows affected");
        }
        
        
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
