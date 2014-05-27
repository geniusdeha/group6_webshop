/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author vahid
 */
@Stateless
public class Pizza {
    private String url_db;
    private ResultSet rs = null;
    private Connection cn = null;
    private PreparedStatement st = null;
    
    public Pizza() {
    }
    
    public Pizza(String _url) throws ClassNotFoundException, SQLException{
      url_db = _url;
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            try {
		cn = DriverManager
		.getConnection(url_db, "root", "root");
 
	} catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return;
	}
 
	if (cn != null) {
		System.out.println("You made it, take control your database now!");
	} else {
		System.out.println("Failed to make connection!");
	}
            
            String query = "select * from AUTHORS";
            st = cn.prepareStatement(query);
            
            rs = st.executeQuery(query);
            System.out.println("4t5ey");
        }
        catch(SQLException sqle){    
        }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    }
    
    
    public void writeResult(PrintWriter out) {
        try {
            int i = 0;
            out.println("in writeresult");
 
            while(rs.next()){
               
                out.print("<p>" + i + " " + rs.getString("NAME") + "</p>");
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pizza.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
 	    try{
              rs.close();
            }
            catch(Exception e) {}
            try{
              st.close();
            }
	    catch(Exception e) {}
            try {
              cn.close();
            }
            catch(Exception e){}
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
