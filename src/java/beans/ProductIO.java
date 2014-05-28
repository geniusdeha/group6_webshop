/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
@Stateless
public class ProductIO {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public static Connection dbConnection;
    public static void initConnection(String databaseUrl) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        try {
            dbConnection = DriverManager
                           .getConnection(databaseUrl, "root", "root");
	} catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
	}
 
	if (dbConnection != null) {
		System.out.println("You made it, take control your database now!");
	} else {
		System.out.println("Failed to make connection!");
	}
    }
    
    public static boolean buyPizza() throws SQLException{
        String queryOlive = "select * from COMPONENTS where COMPONENT_NAME = \"olive\"";
        PreparedStatement st = dbConnection.prepareStatement(queryOlive);
        ResultSet rs = st.executeQuery(queryOlive);
        rs.next();
        int olive = rs.getInt("COUNT");
        
        String queryLettuce = "select * from COMPONENTS where COMPONENT_NAME = \"lettuce\"";
        st = dbConnection.prepareStatement(queryLettuce);
        rs = st.executeQuery(queryLettuce);
        int lettuce;
        rs.next();
        lettuce = rs.getInt("COUNT");
        
        if(olive<1 || lettuce<4){
            return false;
        }
        else{
            String query = "update COMPONENTS set COUNT = COUNT-1 where COMPONENT_NAME = \"olive\";";
            String query2 = "update COMPONENTS set COUNT = COUNT-4 where COMPONENT_NAME = \"lettuce\"";
            st = dbConnection.prepareStatement(query);
            st.execute(query);
            st = dbConnection.prepareStatement(query2);
            st.execute(query2);
            return true;
        }
    }
    
}
