/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
    private Statement st = null;
    
    public Pizza() {
    }
    
    public Pizza(String _url) throws ClassNotFoundException, SQLException{
      url_db = _url;
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url_db);
            st = cn.createStatement();
            String query = "select * from AUTHORS;";
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
                out.println(i);
                out.println(rs.getString("NAME"));
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
