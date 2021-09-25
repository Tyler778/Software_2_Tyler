/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author tyler
 */
public class Contacts {
    private int id;
    private String name;
    private String email;
    /**
     * Constructor for the Contacts Class
     * @param id
     * @param name
     * @param email 
     */
    public Contacts(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    /**
     * Return the ID of the Contact object
     * @return 
     */
    public int getId() {
        return id;
    }
    /**
     * Return the Name of the Contact object
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * Set the Name of the Contact object
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Return the email of the Contact object
     * @return 
     */
    public String getEmail() {
        return email;
    }
    /**
     * Set the email of the Contact object
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
