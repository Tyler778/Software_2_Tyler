/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBAccess.DBAppointments;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import model.Contacts;
import model.Manager;
import java.sql.Timestamp;
import java.time.Instant;

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
    
    public LocalDateTime createDate = null;
    public String createdBy = null;

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
    private TextField typeTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private ComboBox<String> startHourCombo;
    @FXML
    private ComboBox<String> endHourCombo;
    @FXML
    private ComboBox<String> startMinuteCombo;
    @FXML
    private ComboBox<String> endMinuteCombo;
    @FXML
    private ComboBox<String> contactCombo;
    @FXML
    private ComboBox<Integer> custIDCombo;
    @FXML
    private ComboBox<Integer> userIDCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager.deleteAllContactNames();
        fillObservableLists();
        startHourCombo.setItems(hoursOL);
        endHourCombo.setItems(hoursOL);
        startMinuteCombo.setItems(minutesOL);
        endMinuteCombo.setItems(minutesOL);
        contactCombo.setItems(Manager.getAllContactNames());
        custIDCombo.setItems(Manager.getAllCustomerIDs());
        userIDCombo.setItems(Manager.getAllUserIDs());
        
        // TODO
    }    

    
    
    
    public void sendAppointment(Appointment appt) {
        apptTextField.setText(String.valueOf(appt.getId()));
        titleTextField.setText(String.valueOf(appt.getTitle()));
        descTextField.setText(String.valueOf(appt.getDesc()));
        locationTextField.setText(String.valueOf(appt.getLocation()));
        typeTextField.setText(String.valueOf(appt.getType()));
        startDatePicker.setValue(appt.getStartDateTime().toLocalDate());
        endDatePicker.setValue(appt.getEndDateTime().toLocalDate());
        startHourCombo.setValue(String.valueOf(appt.getStartDateTime().getHour()));
        startMinuteCombo.setValue(String.valueOf(appt.getStartDateTime().getMinute()));
        endHourCombo.setValue(String.valueOf(appt.getEndDateTime().getHour()));
        endMinuteCombo.setValue(String.valueOf(appt.getEndDateTime().getMinute()));
        contactCombo.setValue(appt.getContactName());
        custIDCombo.setValue(appt.getCustomerID());
        userIDCombo.setValue(appt.getUserID());
        createDate = appt.getCreateDateTime();
        createdBy = appt.getCreatedBy();
        
        
        
        
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
    private void onActionSaveAppointment(ActionEvent event) throws SQLException, IOException {
        gatherStart();
        gatherEnd();
        
        Manager.updateAppointment(
        Integer.valueOf(apptTextField.getText()),
        titleTextField.getText(),
        descTextField.getText(),
        locationTextField.getText(),
        typeTextField.getText(),
        gatherStart(),
        gatherEnd(),
        createDate,
        createdBy,
        gatherTimestamp(),
        LoginHomeController.userLoggedIn,
        custIDCombo.getValue(),
        userIDCombo.getValue(),
        getContactID(contactCombo.getValue())
        );
        
        SchedulingHomeController.reloadData = true;
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SchedulingHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }
    
    
    
    private LocalDateTime gatherStart() {
        LocalDateTime startDateTime = null;
        
        startDateTime = LocalDateTime.of(startDatePicker.getValue(),LocalTime.of(Integer.valueOf(startHourCombo.getValue()), Integer.valueOf(startMinuteCombo.getValue())));
        System.out.println(startDateTime);
        return startDateTime;
    }
    
    private LocalDateTime gatherEnd() {
        LocalDateTime endDateTime = null;
        
        endDateTime = LocalDateTime.of(endDatePicker.getValue(),LocalTime.of(Integer.valueOf(endHourCombo.getValue()), Integer.valueOf(endMinuteCombo.getValue())));
        System.out.println(endDateTime);
        return endDateTime;
    }
    public static Integer getContactID(String name) {
        int cID = 0;
        for(Contacts contact : Manager.getAllContacts()) {
            if(name.equals(contact.getName())) {
                cID = contact.getId();
            }
        }
        return cID;
    }
    
    private Timestamp gatherTimestamp() {
        return Timestamp.from(Instant.now());
    }
    
}
