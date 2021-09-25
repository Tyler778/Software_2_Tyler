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
public class Users {
    private int id;
    private String name;
    private String password;
    /**
     * Constructor of the Users class
     * @param id
     * @param name
     * @param password 
     */
    public Users(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    /**
     * Returns the ID of the User
     * @return Integer
     */
    public int getId() {
        return id;
    }
    /**
     * Returns the Name of the User
     * @return String
     */
    public String getName() {
        return name;
    }
    /**
     * Set the Name of the User
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Return the password of the User
     * @return String
     */
    public String getPassword() {
        return password;
    }
    /**
     * Set the password of the User
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    
}
