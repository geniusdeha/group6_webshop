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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public static boolean buyPizza(){
        if(substractComponent("olive", 5) &&
           substractComponent("lettuce", 2)){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean buyComputer(){return false;};
    public static boolean buySalad(){return false;};

    
    private static boolean checkAvailability(String item, int need){
        int available = 0;
        try {
            String query = "select * from COMPONENTS where COMPONENT_NAME = \"" + item + "\"";
            PreparedStatement st = dbConnection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            rs.next();
            available = rs.getInt("AMOUNT");
        } catch (SQLException ex) {
            Logger.getLogger(ProductIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(available < need){
            return false;
        }
        else
            return true;
    }
    public static boolean substractComponent(String item, int amount){
        if(checkAvailability(item, amount)){
            try {
                String query = "update COMPONENTS set AMOUNT = AMOUNT - " + amount +
                               " where COMPONENT_NAME = \"" + item + "\";";
                PreparedStatement st = dbConnection.prepareStatement(query);
                st.execute(query);
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductIO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean addComponent(String item, int amount){
        try {
            String query = "update COMPONENTS set AMOUNT = AMOUNT  + " + amount +
                           " where COMPONENT_NAME = \"" + item + "\";";
            PreparedStatement st = dbConnection.prepareStatement(query);
            st.execute(query);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductIO.class.getName()).log(Level.SEVERE, null, ex);
        }
            return true;
    }
    
    public static ProductList readProducts(){
        ProductList list = new ProductList(productCount());
        try {
            String query = "select * from PRODUCTS;";
            PreparedStatement st = dbConnection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Product p = new Product();
                p.setName(rs.getString("product_name"));
                p.setComponents(rs.getString("component_list"));
                p.setPrice(rs.getInt("price"));
                list.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static ComponentList readComponents(){
        ComponentList list = new ComponentList(componentCount());
        try {
            String query = "select * from COMPONENTS;";
            PreparedStatement st = dbConnection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Component c = new Component();
                c.setName(rs.getString("component_name"));
                c.setAmount(rs.getInt("amount"));
                list.add(c);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    private static int productCount(){
        try {
            String query = "select count(*) from products;";
            PreparedStatement st = dbConnection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            st.close();
            return count; 
       } catch (SQLException ex) {
            Logger.getLogger(ProductIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    private static int componentCount(){
        try {
            String query = "select count(*) from components;";
            PreparedStatement st = dbConnection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            st.close();
            return count; 
       } catch (SQLException ex) {
            Logger.getLogger(ProductIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
