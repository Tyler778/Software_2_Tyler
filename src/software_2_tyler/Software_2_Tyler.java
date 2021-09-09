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
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
        Statement statement = DBQuery.getStatement(); //Set Statement reference
        
        
        String selectStatement = "SELECT * FROM appointments";
        
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        
        while(rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            LocalDate date = rs.getDate("Create_Date").toLocalDate();
            LocalTime time = rs.getTime("Create_Date").toLocalTime();
            String createdBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
            String updatedBy = rs.getString("Last_Updated_By");
            
            System.out.println(countryID + " | " + countryName + " | " + date + time + " | " + createdBy + " | " + lastUpdate + " | " + updatedBy);
            
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
