/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Utilities.DBConnection;
import Utilities.DBQuery;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import model.Divisions;
import model.Manager;



/**
 *
 * @author tyler
 */
public class DBDivisions {
    
    
    
    
    
    public static void loadDivisions () throws SQLException {
        
        DBQuery.setStatement(DBConnection.getConnection());
        Statement statement = DBQuery.getStatement();
        
        String selectDivisionsStatement = "SELECT * FROM first_level_divisions";
        
        statement.execute(selectDivisionsStatement);
        ResultSet dSet = statement.getResultSet();
        
        
        while(dSet.next()) {
            int divID = dSet.getInt("Division_ID");
            String divName = dSet.getString("Division");
            int countryID = dSet.getInt("COUNTRY_ID");
            Divisions div = new Divisions(divID, divName, countryID);
            Manager.addDivision(div);
        }
    }
    
}
