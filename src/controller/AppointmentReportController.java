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
    @FXML
    private Button resetButton;

    /**
     * Initializes the controller class.  Sets the table to have appropriate values and preps the page for utility.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resetButton.setVisible(false);
        appointmentsTable.setItems(generateAppointmentsOL());
        monthCombo.setItems(SchedulingHomeController.months);
        custCombo.setItems(Manager.getAllCustomerNames());
        listTypes.setItems(sort());
        
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        totalLabel.setText(String.valueOf(generateAppointmentsOL().size()));
        
    }    
    /**
     * Sets the stage to Scheduling Home FXML.
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
     * Repopulates the appointmentsTable with new data as well as the typeListView.  Sets the totalLabel as well as enables the reset button.
     * @param event 
     */
    @FXML
    private void comboAction(ActionEvent event) {
        //listTypes.setItems(null);
        //appointmentsTable.setItems(generateAppointmentsOL();
        appointmentsTable.setItems(generateAppointmentsOL());
        listTypes.setItems(sort());
        totalLabel.setText(String.valueOf(generateAppointmentsOL().size()));
        resetButton.setVisible(true);

        //generateTypeOL
        
        
    }
    
    /**
     * Returns an Observable List of Appointments to be used in the main tableview that are selected upon matching criteria from the comboboxes that have been modified.  
     * @return ObservableList<Appointment> 
     */
    private ObservableList<Appointment> generateAppointmentsOL() {
        ObservableList<Appointment>holdAppt = FXCollections.observableArrayList();
        if(monthCombo.getValue() == null && custCombo.getValue() != null) {
            for(Appointment appt : Manager.getAllAppointments()) {
                if(Manager.getSpecificCustomerID(custCombo.getValue()) == appt.getCustomerID()) {
                    holdAppt.add(appt);
                }
            }
            return holdAppt;
        } else if(monthCombo.getValue() != null && custCombo.getValue() != null) {
            for(Appointment appt : Manager.getAllAppointments()) {
                if(Manager.getSpecificCustomerID(custCombo.getValue()) == appt.getCustomerID() && Month.from(Month.valueOf(monthCombo.getValue().toUpperCase())) == Month.from(appt.getStartDateTime())) {
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
    
    
    
    /**
     * Returns an Observable List of Strings that are made up of the different distinct types of Appointments with a count of the amount of such type.  Uses a hashmap to create this.  
     * @return 
     */
    public ObservableList<String> sort() {
        mapper.clear();
        displayAppointments.clear();
        ObservableList<String>holdTypes = FXCollections.observableArrayList();
        if(monthCombo.getValue() == null && custCombo.getValue() != null) {
            int count = 1;
            System.out.println(generateAppointmentsOL());
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
        else {
            int count = 1;
            for(Appointment appt: generateAppointmentsOL()) {
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
        }
        return holdTypes;
    }
    /**
     * Sets the appointmentsTable to have repopulated data based on new combinations. Sets the listTypes to have appropriate values as well.  Updates totalLabel and resetButton.
     * @param event 
     */
    @FXML
    private void comboMonthAction(ActionEvent event) {
        appointmentsTable.setItems(generateAppointmentsOL());
        listTypes.setItems(sort());
        totalLabel.setText(String.valueOf(generateAppointmentsOL().size()));
        resetButton.setVisible(true);
    }
    /**
     * Sets the comboBoxes to null values and reupdates the data to be default with no restrictions.  Removes the ability to see the reset button.
     * @param event 
     */
    @FXML
    private void onActionReset(ActionEvent event) {
        custCombo.setValue(null);
        monthCombo.setValue(null);
        appointmentsTable.setItems(generateAppointmentsOL());
        listTypes.setItems(sort());
        totalLabel.setText(String.valueOf(generateAppointmentsOL().size()));
        resetButton.setVisible(false);
    }
    
}
