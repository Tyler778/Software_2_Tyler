/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
public class AppointmentReportController implements Initializable {
    
    Stage stage;
    Parent scene;
    public static Map<String,Integer> mapper = new HashMap<>();
    public static ObservableList<Appointment>displayAppointments = FXCollections.observableArrayList();

    @FXML
    private TableView<Appointment> appointmentsTable;
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
    private ComboBox<String> custCombo;
    @FXML
    private ListView<String> listTypes;
    @FXML
    private ComboBox<String> monthCombo;
    @FXML
    private Label totalLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appointmentsTable.setItems(generateAppointmentsOL());
        monthCombo.setItems(SchedulingHomeController.months);
        custCombo.setItems(Manager.getAllCustomerNames());
        
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        
    }    

    @FXML
    private void onActionMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/SchedulingHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void comboAction(ActionEvent event) {
        //listTypes.setItems(null);
        //appointmentsTable.setItems(generateAppointmentsOL();
        appointmentsTable.setItems(generateAppointmentsOL());
        listTypes.setItems(sort());
        
        //generateTypeOL
        
        
    }
    private ObservableList<Appointment> generateAppointmentsOL() {
        ObservableList<Appointment>holdAppt = FXCollections.observableArrayList();
        if(monthCombo.getValue() == null && custCombo.getValue() != null) {
            for(Appointment appt : Manager.getAllAppointments()) {
                if(Manager.getSpecificCustomerID(custCombo.getValue()) == appt.getId()) {
                    holdAppt.add(appt);
                }
            }
            return holdAppt;
        } else if(monthCombo.getValue() != null && custCombo.getValue() != null) {
            for(Appointment appt : Manager.getAllAppointments()) {
                if(Manager.getSpecificCustomerID(custCombo.getValue()) == appt.getId() && Month.from(Month.valueOf(monthCombo.getValue().toUpperCase())) == Month.from(appt.getStartDateTime())) {
                   holdAppt.add(appt);
                }
            }
            return holdAppt;
        } else if(monthCombo.getValue() != null && custCombo.getValue() == null) {
            for(Appointment appt : Manager.getAllAppointments()) {
                if(Month.from(Month.valueOf(monthCombo.getValue().toUpperCase())) == Month.from(appt.getStartDateTime())) {
                    holdAppt.add(appt);
                }
            }
            return holdAppt;
        } else if(monthCombo.getValue() == null && custCombo.getValue() == null) {
            holdAppt.addAll(Manager.getAllAppointments());
        }
        return holdAppt;
    }
    ////
    ////
    //// LAMBDA 
    ////
    ////
    ////
    
    
    
    
    public ObservableList<String> sort() {
        mapper.clear();
        displayAppointments.clear();
        ObservableList<String>holdTypes = FXCollections.observableArrayList();
        
        if(monthCombo.getValue() == null && custCombo.getValue() != null) {
            int count = 1;
            for(Appointment appt : generateAppointmentsOL()) {
                if(mapper.containsKey(appt.getType())) {
                    int save = mapper.get(appt.getType());
                    mapper.put(appt.getType(), save + 1);
                } else {
                    mapper.put(appt.getType(), count);
                } 
            }
        
        mapper.forEach((k, v) -> {
            holdTypes.add(k + " : " + v);
        });
        } else if(monthCombo.getValue() != null && custCombo.getValue() != null) {
            int count = 1;
            for(Appointment appt : generateAppointmentsOL()) {
                if(Month.from(Month.valueOf(monthCombo.getValue().toUpperCase())) == Month.from(appt.getStartDateTime())) {
                    if(mapper.containsKey(appt.getType())) {
                        int save = mapper.get(appt.getType());
                        mapper.put(appt.getType(), save + 1);
                    } else {
                        mapper.put(appt.getType(), count);
                    } 
                }
                
            }
            
          mapper.forEach((k, v) -> {
            holdTypes.add(k + " : " + v);
        });         
        } else if(monthCombo.getValue() != null && custCombo.getValue() == null) {
            int count = 1;
            for(Appointment appt : generateAppointmentsOL()) {
                if(Month.from(Month.valueOf(monthCombo.getValue().toUpperCase())) == Month.from(appt.getStartDateTime())) {
                    if(mapper.containsKey(appt.getType())) {
                        int save = mapper.get(appt.getType());
                        mapper.put(appt.getType(), save + 1);
                    } else {
                        mapper.put(appt.getType(), count);
                    } 
                }
                
            }
            
          mapper.forEach((k, v) -> {
            holdTypes.add(k + " : " + v);
        });         
        }
        return holdTypes;
    }

    @FXML
    private void comboMonthAction(ActionEvent event) {
        appointmentsTable.setItems(generateAppointmentsOL());
        listTypes.setItems(sort());
    }
    
}
