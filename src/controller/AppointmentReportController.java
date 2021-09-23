/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Appointment;

/**
 * FXML Controller class
 *
 * @author tyler
 */
public class AppointmentReportController implements Initializable {

    @FXML
    private TableView<Appointment> appointmentsTable;
    @FXML
    private TableColumn<Appointment, ?> apptIDCol;
    @FXML
    private TableColumn<Appointment, ?> titleCol;
    @FXML
    private TableColumn<Appointment, ?> descCol;
    @FXML
    private TableColumn<Appointment, ?> locationCol;
    @FXML
    private TableColumn<Appointment, ?> typeCol;
    @FXML
    private TableColumn<Appointment, ?> startCol;
    @FXML
    private TableColumn<Appointment, ?> endCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionMainMenu(ActionEvent event) {
    }
    
}