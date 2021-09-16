/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import Utilities.DBConnection;
import model.Manager;
/**
 *
 * @author tyler
 */
public class DBCountries {
    
    
    public static void loadCountries() {
        
        
        
        try {
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Countries C = new Countries(countryId, countryName);
                Manager.addCountry(C);
                Manager.addCountryName(countryName);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}