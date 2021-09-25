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
public class Customers {
    private int id;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDateTime;
    private String createdBy;
    private LocalDateTime updateDateTime;
    private String updatedBy;
    private int divID;
    private String divName;
    private String country;

    /**
     * Constructor for the Customer class.  Obtains some information from Foreign key tables.
     * @param id
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param createDateTime
     * @param createdBy
     * @param updateDateTime
     * @param updatedBy
     * @param divID
     * @param divName
     * @param country 
     */
    public Customers(int id, String customerName, String address, String postalCode, String phone, LocalDateTime createDateTime, String createdBy, LocalDateTime updateDateTime, String updatedBy, int divID, String divName, String country) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDateTime = createDateTime;
        this.createdBy = createdBy;
        this.updateDateTime = updateDateTime;
        this.updatedBy = updatedBy;
        this.divID = divID;
        this.divName = divName;
        this.country = country;
    }
    /**
     * Return the ID of the Customers object
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Get the Name of the Customers object
     * @return 
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * Set the Name of the Customers object
     * @param customerName 
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     * Return the Address of the Customers object
     * @return 
     */
    public String getAddress() {
        return address;
    }
    /**
     * Set the Address of the Customers object
     * @param address 
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Return the postal code of the Customers object
     * @return 
     */
    public String getPostalCode() {
        return postalCode;
    }
    /**
     * Set the postal code of the Customers object
     * @param postalCode 
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    /**
     * Return the Phone number of the Customers object
     * @return 
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Set the phone number of the Customers object
     * @param phone 
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * Return the create LocalDateTime of the Customers object
     * @return 
     */
    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }
    /**
     * Set the create LocalDateTime of the Customers object
     * @param createDateTime 
     */
    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }
    /**
     * Return the Created_By of the Customers object
     * @return 
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Set the Created_By of the Customers object
     * @param createdBy 
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * Return the updated LocalDateTime of the Customers object
     * @return 
     */
    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }
    /**
     * Set the updated LocalDateTime of the Customers object
     * @param updateDateTime 
     */
    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
    /**
     * Return the Updated_By of the Customers object
     * @return 
     */
    public String getUpdatedBy() {
        return updatedBy;
    }
    /**
     * Set the Updated_By of the Customers object
     * @param updatedBy 
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    /**
     * Return the Division_ID foreign key of the Customers object
     * @return 
     */
    public int getDivID() {
        return divID;
    }
    /**
     * Set the Division_ID foreign key of the Customers object
     * @param divID 
     */
    public void setDivID(int divID) {
        this.divID = divID;
    }
    /**
     * Return the DivisionName of the Customers object
     * @return 
     */
    public String getDivName() {
        return divName;
    }
    /**
     * Set the DivisionName of the Customers object
     * @param divName 
     */
    public void setDivName(String divName) {
        this.divName = divName;
    }
    /**
     * Return the Country of the Customers object
     * @return 
     */
    public String getCountry() {
        return country;
    }
    /**
     * Set the Country of the Customers object
     * @param country 
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    
    
    
}
