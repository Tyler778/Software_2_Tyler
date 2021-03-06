/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customers;
import model.Manager;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author tyler
 */
public class SchedulingHomeController implements Initializable {
    public static ObservableList<String>months = FXCollections.observableArrayList();
    private static ObservableList<String>allWeeks = FXCollections.observableArrayList();
    Stage stage;
    Parent scene;
    
    public static Boolean reloadData = false;
    

    
    @FXML
    private TableView<Appointment> tableAppointments;
    @FXML
    private TableColumn<Appointment, Integer> apptIDCol;
    
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, String> contactCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> startCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> endCol;
    @FXML
    private TableColumn<Appointment, Integer> customerCol;
    @FXML
    private TableView<Customers> tableCustomers;
    @FXML
    private TableColumn<Customers, Integer> customerIDCol;
    @FXML
    private TableColumn<Customers, String> nameCol;
    @FXML
    private TableColumn<Customers, String> addressCol;
    @FXML
    private TableColumn<Customers, String> postalCol;
    @FXML
    private TableColumn<Customers, String> phoneCol;
    @FXML
    private Label aptDeleteMsg;
    @FXML
    private Label aptModifyMsg;
    @FXML
    private Label custModifyMsg;
    @FXML
    private Label custDeleteMsg;
    @FXML
    private TableColumn<Appointment, Integer> userCol;
    @FXML
    private Label remainingAppointmentLabel;
    @FXML
    private ComboBox<String> monthCombo;
    @FXML
    private ComboBox<String> weekCombo;
    @FXML
    private TableColumn<Customers, String> countryCol;
    @FXML
    private TableColumn<Customers, String> firstLevelCol;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        remainingAppointmentLabel.setVisible(false);
        aptDeleteMsg.setVisible(false);
        aptModifyMsg.setVisible(false);
        custModifyMsg.setVisible(false);
        custDeleteMsg.setVisible(false);
        if(reloadData) {
            try {
                reloadData();
            } catch (SQLException ex) {
                Logger.getLogger(SchedulingHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        
        fillOL();
        dataIntoTable();
        
      

    }    

    @FXML
    private void onActionAddAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionModifyAppointment(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyAppointment.fxml"));
            loader.load();
            ModifyAppointmentController ModApptController = loader.getController();
            ModApptController.sendAppointment(tableAppointments.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(Exception e) {
            aptDeleteMsg.setVisible(false);
            aptModifyMsg.setVisible(true);
        }
    }

    @FXML
    private void onActionDeleteAppointment(ActionEvent event) throws SQLException {
        
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete Appointment ID " + tableAppointments.getSelectionModel().getSelectedItem().getId() + ", '" + tableAppointments.getSelectionModel().getSelectedItem().getType() + "',"
                    + " are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                Manager.removeAppointment(tableAppointments.getSelectionModel().getSelectedItem());
            }
            
            
            
            
            
        } catch (Exception e) {
            aptModifyMsg.setVisible(false);
            aptDeleteMsg.setVisible(true);
        }
        
        
    }

    @FXML
    private void onActionDeleteCustomer(ActionEvent event) throws SQLException {
        try {
          if(Manager.checkAppointments(tableCustomers.getSelectionModel().getSelectedItem().getId())) {
            remainingAppointmentLabel.setVisible(true);
        } else {
           try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete Customer?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                Manager.removeCustomer(tableCustomers.getSelectionModel().getSelectedItem());
                remainingAppointmentLabel.setVisible(false); 
            }
            
            
            
            
            
            
        } catch (Exception e) {
            
            } 
        }  
        } catch(Exception e) {
            custModifyMsg.setVisible(false);
            custDeleteMsg.setVisible(true);
        }
        
        
        
        
        
        
        
    }

    @FXML
    private void onActionModifyCustomer(ActionEvent event) throws IOException {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyCustomer.fxml"));
            loader.load();
            ModifyCustomerController ModCusController = loader.getController();
            ModCusController.sendCustomer(tableCustomers.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(Exception e) {
            custModifyMsg.setVisible(true);
            custDeleteMsg.setVisible(false);
        }
    }

    @FXML
    private void onActionAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    

    
    private void reloadData() throws SQLException {
        System.out.println("Data Reloaded");
        Manager.deleteData();
        Manager.loadData();
    }

    @FXML
    private void onActionExit(ActionEvent event) {
        System.exit(0);
    }
    
    private void dataIntoTable() {
        tableAppointments.setItems(Manager.getAllAppointments());
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        
        tableCustomers.setItems(Manager.getAllCustomers());
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        firstLevelCol.setCellValueFactory(new PropertyValueFactory<>("divName"));
        monthCombo.setItems(months);
        weekCombo.setItems(allWeeks);
    }
    
    
    
    
    public static void alertCheck() {
        if(Manager.checkAppointmentProximity()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Appointment ID " + LoginHomeController.apptMatchID + " Starts at " + LoginHomeController.apptMatchStart);
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You have no appointments within 15 minutes");
            Optional<ButtonType> result = a.showAndWait();
        }
    }

    @FXML
    private void onActionModificationReport(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModificationReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionScheduleReport(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ScheduleReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionAppointmentReport(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    /**
     * Populates the observable list months and allWeeks with data that is current and accurate.
     */
    public static void fillOL() {
        months.clear();
        allWeeks.clear();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        int i = 0;
        int x = -6;
        while(i < 104) {
            allWeeks.add(String.valueOf(LocalDate.now().plusDays(x)) + " - " + String.valueOf(LocalDate.now().plusDays(x + 6)));
            x += 7;
            i++;
        }
        
        
        
    }
    /**
     * Method compares the month selected with the combo box month to all appointments start date month and if they match, adds it to the observable list to be populated in the appointments table.
     * @param event 
     */
    @FXML
    private void comboMonthAction(ActionEvent event) {
        ObservableList<Appointment>monthsSort = FXCollections.observableArrayList();
        
        try {
            for(Appointment appt : Manager.getAllAppointments()) {
                if(Month.from(Month.valueOf(monthCombo.getValue().toUpperCase())) == Month.from(appt.getStartDateTime())) {
                    monthsSort.add(appt);
                }
            }
            
            
            
        } catch(Exception e) {
            
        }
        
        tableAppointments.setItems(monthsSort);
    }
    /**
     * With the list of strings formatted as two dates, this method splits the dates apart, sets them as beginning and end dates, then checks all appointments to see which appointments have a start time between those dates.  If the appointment does, it is added to the Observable List and finally tableAppoiontments items are set as the new filtered observable list.
     * @param event 
     */
    @FXML
    private void comboWeekAction(ActionEvent event) {
        try {
            String[] arrStr = weekCombo.getValue().split(" - ");
            LocalDate begDate = LocalDate.parse(arrStr[0]).minusDays(1);
            LocalDate endDate = LocalDate.parse(arrStr[1]).plusDays(1);
            ObservableList<Appointment>weekSort = FXCollections.observableArrayList();
            for(Appointment appt : Manager.getAllAppointments()) {
                if(appt.getStartDateTime().toLocalDate().isBefore(endDate) && appt.getStartDateTime().toLocalDate().isAfter(begDate)) {
                    weekSort.add(appt);
                }
            }
            tableAppointments.setItems(weekSort);
        } catch(Exception e) {
            
        }
        
        
    }
    /**
     * Sets the combo boxes Month and Week to null and sets the tableView to default all appointments.
     * @param event 
     */
    @FXML
    private void onActionResetSortMonth(ActionEvent event) {
        monthCombo.setValue(null);
        weekCombo.setValue(null);
        tableAppointments.setItems(Manager.getAllAppointments());
    }
    
}
