/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author tyler
 */
public class Appointment {
    private int id;
    private String title;
    private String desc;
    private String location;
    private String type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LocalDateTime createDateTime;
    private String createdBy;
    private LocalDateTime updateDateTime;
    private String updatedBy;
    private int customerID;
    private int userID;
    private int contactID;
    private String contactName;
    /**
     * Constructor for Appointment Class that has fields that are foreign keys and some additional data than that just in the Appointment Table
     * @param id
     * @param title
     * @param desc
     * @param location
     * @param type
     * @param startDateTime
     * @param endDateTime
     * @param createDateTime
     * @param createdBy
     * @param updateDateTime
     * @param updatedBy
     * @param customerID
     * @param userID
     * @param contactID
     * @param contactName 
     */
    public Appointment(int id, String title, String desc, String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, LocalDateTime createDateTime, String createdBy, LocalDateTime updateDateTime, String updatedBy, int customerID, int userID, int contactID, String contactName) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.createDateTime = createDateTime;
        this.createdBy = createdBy;
        this.updateDateTime = updateDateTime;
        this.updatedBy = updatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.contactName = contactName;
    }
    
    
    /**
     * Return the ID of the Appointment object
     * @return 
     */
    public int getId() {
        return id;
    }
    /**
     * Return the Title of the Appointment object
     * @return 
     */
    public String getTitle() {
        return title;
    }
    /**
     * Set the Title of the Appointment object
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Return the description of the Appointment object
     * @return 
     */
    public String getDesc() {
        return desc;
    }
    /**
     * Set the description of the Appointment object
     * @param desc 
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * Return the location of the Appointment object
     * @return 
     */
    public String getLocation() {
        return location;
    }
    /**
     * Set the location of the Appointment object
     * @param location 
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * Return the type of the Appointment object
     * @return 
     */
    public String getType() {
        return type;
    }
    /**
     * Set the type of the Appointment object
     * @param type 
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Return the Start of the Appointment object
     * @return 
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
    /**
     * Set the Start of the Appointment object
     * @param startDateTime 
     */
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
    /**
     * Return the End of the Appointment object
     * @return 
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
    /**
     * Set the End of the Appointment object
     * @param endDateTime 
     */
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
    /**
     * Return the Create LocalDateTime of the Appointment object
     * @return 
     */
    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }
    /**
     * Set the Create LocalDateTime of the Appointment object
     * @param createDateTime 
     */
    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }
    /**
     * Return the Created_By of the Appointment object
     * @return 
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Set the Created_By of the Appointment object
     * @param createdBy 
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * Return the Updated LocalDateTime from the Appointment object
     * @return 
     */
    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }
    /**
     * Set the Updated LocalDateTime from the Appointment object
     * @param updateDateTime 
     */
    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
    /**
     * Return the Updated_By of the Appointment object
     * @return 
     */
    public String getUpdatedBy() {
        return updatedBy;
    }
    /**
     * Set the Updated_By of the Appointment object
     * @param updatedBy 
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    /**
     * Return the Customer_ID of the Appointment object
     * @return 
     */
    public int getCustomerID() {
        return customerID;
    }
    /**
     * Set the Customer_ID of the Appointment object
     * @param customerID 
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    /**
     * Return the User_ID of the Appointment object
     * @return 
     */
    public int getUserID() {
        return userID;
    }
    /**
     * Set the User_ID of the Appointment object
     * @param userID 
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    /**
     * Return the Contact_ID of the Appointment object
     * @return 
     */
    public int getContactID() {
        return contactID;
    }
    /**
     * Set the Contact_ID of the Appointment object
     * @param contactID 
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    /**
     * Return the ContactName of the Appointment object based on the Contact_ID which is a foreign key in the Appointments Table
     * @return 
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * Set the ContactName of the Appointment object based on the Contact_ID which is a foreign key in the Appointments Table
     * @param contactName 
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    
    
    
    
}

