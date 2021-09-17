/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;

/**
 * FXML Controller class
 *
 * @author tyler
 */
public class ModifyAppointmentController implements Initializable {
    
    public static ObservableList<String>hoursOL = FXCollections.observableArrayList();
    public static ObservableList<String>minutesOL = FXCollections.observableArrayList();
    
    Stage stage;
    Parent scene;

    @FXML
    private DatePicker endDatePicker;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private TextField apptTextField;
    @FXML
    private TextField descTextField;
    @FXML
    private TextField locationTextField;
    @FXML
    private TextField contactTextField;
    @FXML
    private TextField typeTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField customerIDTextField;
    @FXML
    private ComboBox<String> startHourCombo;
    @FXML
    private ComboBox<String> endHourCombo;
    @FXML
    private ComboBox<String> startMinuteCombo;
    @FXML
    private ComboBox<String> endMinuteCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillObservableLists();
        startHourCombo.setItems(hoursOL);
        endHourCombo.setItems(hoursOL);
        startMinuteCombo.setItems(minutesOL);
        endMinuteCombo.setItems(minutesOL);
        
        // TODO
    }    

    
    
    
    public void sendAppointment(Appointment appt) {
        apptTextField.setText(String.valueOf(appt.getId()));
        customerIDTextField.setText(String.valueOf(appt.getCustomerID()));
        titleTextField.setText(String.valueOf(appt.getTitle()));
        descTextField.setText(String.valueOf(appt.getDesc()));
        locationTextField.setText(String.valueOf(appt.getLocation()));
        contactTextField.setText(String.valueOf(appt.getContactName()));
        typeTextField.setText(String.valueOf(appt.getType()));
        
        
        
    }
    public void fillObservableLists() {
        int hours = 0;
        int minutes = 0;
        while (hours < 24) {
            hoursOL.add(String.valueOf(hours));
            hours ++;
        }
        while (minutes < 60) {
            minutesOL.add(String.valueOf(minutes));
            minutes ++;
        }
    }


    @FXML
    private void onActionCancelModifyAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SchedulingHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionSaveAppointment(ActionEvent event) {
        gatherStart();
        
    }
    
    
    
    private LocalDateTime gatherStart() {
        LocalDateTime startDateTime = null;
        
        startDateTime = LocalDateTime.of(startDatePicker.getValue(), LocalTime.parse(startHourCombo.getValue() + startMinuteCombo.getValue()));
        System.out.println(startDateTime);
        return startDateTime;
    }
    
}
