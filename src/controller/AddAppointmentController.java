/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.Contacts;
import model.Manager;

/**
 * FXML Controller class
 *
 * @author tyler
 */
public class AddAppointmentController implements Initializable {
    
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
    @FXML
    private Label businessHourError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        businessHourError.setVisible(false);
        apptTextField.setDisable(true);
        fillObservableLists();
        startHourCombo.setItems(hoursOL);
        endHourCombo.setItems(hoursOL);
        startMinuteCombo.setItems(minutesOL);
        endMinuteCombo.setItems(minutesOL);
        contactCombo.setItems(Manager.getAllContactNames());
        custIDCombo.setItems(Manager.getAllCustomerIDs());
        userIDCombo.setItems(Manager.getAllUserIDs());
        startHourCombo.setValue("0");
        endHourCombo.setValue("0");
        startMinuteCombo.setValue("0");
        endMinuteCombo.setValue("0");

        
        
        // TODO
    }    

    @FXML
    private void onActionCancelAddAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SchedulingHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionAddAppointment(ActionEvent event) throws SQLException, IOException {  
        System.out.println(checkOtherTimeConflicts(gatherStart(), gatherEnd()));
        //ADD IN CHECK OTHER TIME CONFLICTS!!!!
        //
        //
        //
        // DO IT
        try {
           if(checkValidTime(gatherStart(), gatherEnd())) {
                Manager.addAppointment(
                titleTextField.getText(),
                descTextField.getText(),
                locationTextField.getText(),
                typeTextField.getText(),
                gatherStart(),
                gatherEnd(),
                createDate(),
                LoginHomeController.userLoggedIn,
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
            } else {
               businessHourError.setVisible(true);
           }
        } catch(Exception e) {
            businessHourError.setVisible(true);
        }   
        
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
    
    private LocalDateTime gatherStart() {
        LocalDateTime startDateTime = null;
        startDateTime = LocalDateTime.of(startDatePicker.getValue(),LocalTime.of(Integer.valueOf(startHourCombo.getValue()), Integer.valueOf(startMinuteCombo.getValue())));
        return startDateTime;
    }
    
    private LocalDateTime gatherEnd() {
        LocalDateTime endDateTime = null;
        endDateTime = LocalDateTime.of(endDatePicker.getValue(),LocalTime.of(Integer.valueOf(endHourCombo.getValue()), Integer.valueOf(endMinuteCombo.getValue())));
        return endDateTime;
    }
    private static LocalDateTime createDate() {
        LocalDateTime current = null;
        current = LocalDateTime.now();
        return current;
        
    }
    private Timestamp gatherTimestamp() {
        return Timestamp.from(Instant.now());
    }
    private Integer getContactID(String name) {
        int cID = 0;
        for(Contacts contact : Manager.getAllContacts()) {
            if(name.equals(contact.getName())) {
                cID = contact.getId();
            }
        }
        return cID;
    }
    
    private Boolean checkValidTime(LocalDateTime start, LocalDateTime end) {
        
        ZonedDateTime startBusinessHours = ZonedDateTime.of(LocalDateTime.of(3, 3, 3, 8, 0, 0, 0), ZoneId.of("America/New_York"));
        
        ZonedDateTime endBusinessHours = ZonedDateTime.of(LocalDateTime.of(3, 3, 3, 22, 0, 0, 0), ZoneId.of("America/New_York"));
        //System.out.println(startBusinessHours.toLocalTime());
        //System.out.println(endBusinessHours.toLocalTime());
        //System.out.println(start.atZone(ZoneId.of("America/New_York")));
        //System.out.println(end.atZone(ZoneId.of("America/New_York")));
        
        if(start.atZone(ZoneId.of("America/New_York")).toLocalTime().isAfter(startBusinessHours.toLocalTime()) && end.atZone(ZoneId.of("America/New_York")).toLocalTime().isBefore(endBusinessHours.toLocalTime())) {
            System.out.println("Yes");
            return true;
        }
        
        return false;
        
    }
    private Boolean checkOtherTimeConflicts(LocalDateTime start, LocalDateTime end) {
        Boolean returnValue = false;
        for (Appointment appt : Manager.getAllAppointments()) {
            LocalDateTime apptStart = appt.getStartDateTime();
            LocalDateTime apptEnd = appt.getEndDateTime();
            if((start.isBefore(apptEnd) && start.isAfter(apptStart)) || (end.isBefore(apptEnd) && end.isAfter(apptStart)))  {
                returnValue = true;
            } else {
                //
            }
        }
        return returnValue;
    }
    
}
