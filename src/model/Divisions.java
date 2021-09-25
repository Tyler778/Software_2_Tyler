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
public class Divisions {
    private int id;
    private String name;
    private int countryID;
    /**
     * Constructor of the Divisions class
     * @param id
     * @param name
     * @param countryID 
     */
    public Divisions(int id, String name, int countryID) {
        this.id = id;
        this.name = name;
        this.countryID = countryID;
    }
    /**
     * Return the Division_ID of the Divisions object
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Return the Name of the Divisions object
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * Set the Name of the Divisions object
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Return the Country_ID of the Divisions object, a Foreign key in the Divisions Table
     * @return 
     */
    public int getCountryID() {
        return countryID;
    }
    /**
     * Set the Country_ID of the Divisions object, a Foreign key in the Divisions Table
     * @param countryID 
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
            
    
}
