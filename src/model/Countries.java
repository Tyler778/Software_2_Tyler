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
public class Countries {
    private int id;
    private String name;
    
    
    public Countries(int id, String name) {
        this.id = id;
        this.name = name;
        
    }
    
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}
