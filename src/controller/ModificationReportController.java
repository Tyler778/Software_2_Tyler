/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Manager;

/**
 * FXML Controller class
 *
 * @author tyler
 */
public class ModificationReportController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<Appointment, Integer> apptIDCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> startCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> endCol;
    @FXML
    private ComboBox<String> userCombo;
    @FXML
    private TableColumn<Appointment, LocalDateTime> updateCol;

    /**
     * Initializes the controller class and sets the userComboBox to the appropriate Observable List.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userCombo.setItems(Manager.getAllUserNames());
        
        // TODO
    }    
    /**
     * Sets the stage to the Scheduling Home FXML.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void onActionMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SchedulingHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
        
        
    }
    /**
     * Fills the appointmentTable with the appropriate Observable List.
     * @param user 
     */
    private void fillTable(String user) {
        appointmentTable.setItems(Manager.filterAppointmentsByUser(user));
        //apptCount.setText(String.valueOf(Manager.getFilteredAppointments(contact).size()));
        
    }

    
    /**
     * Sets the appointmentTable with data based on the selected User.
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void comboAction(ActionEvent event) throws SQLException {
        
        fillTable(userCombo.getValue());
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        updateCol.setCellValueFactory(new PropertyValueFactory<>("updateDateTime"));
        //customerCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        
                
    }
    
}
