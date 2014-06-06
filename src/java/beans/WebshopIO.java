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

/**
 *
 * @author HP
 */
@Stateless
public class WebshopIO {
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
    
    public static boolean buyPizza(int quantity){
        if(checkAvailability("olive", 5*quantity) &&
           checkAvailability("lettuce", 2*quantity)){
            substractComponent("olive", 5*quantity);
            substractComponent("lettuce", 2*quantity);
            return true;
        }      
        return false;
    }
    public static boolean buyComputer(int quantity){
        if(checkAvailability("HDD", 1*quantity) &&
            checkAvailability("keyboard", 1*quantity) &&
            checkAvailability("mouse", 1*quantity)){
            substractComponent("HDD", 1*quantity);
            substractComponent("keyboard", 1*quantity);            
            substractComponent("mouse", 1*quantity);
            return true;
        }
        return false;
    }
    public static boolean buySalad(int quantity){
        if(checkAvailability("olive", 5*quantity) &&
            checkAvailability("lettuce", 3*quantity) &&
            checkAvailability("tomato", 1*quantity)){
            substractComponent("olive", 5*quantity);
            substractComponent("lettuce", 3*quantity);
            substractComponent("tomato", 1*quantity);
            return true;
        }
        return false;
    }

    
    private static boolean checkAvailability(String item, int need){
        int available = 0;
        try {
            String query = "select * from COMPONENTS where COMPONENT_NAME = \"" + item + "\";";
            PreparedStatement st = dbConnection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            rs.next();
            available = rs.getInt("AMOUNT");
        } catch (SQLException ex) {
            Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    private static int customerCount(){
        try {
            String query = "select count(*) from customers;";
            PreparedStatement st = dbConnection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            st.close();
            return count; 
       } catch (SQLException ex) {
            Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    private static int orderCount(){
        try {
            String query = "select count(*) from orders;";
            PreparedStatement st = dbConnection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            st.close();
            return count; 
       } catch (SQLException ex) {
            Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public static Customer readCustomer(int customerID){
        Customer customer = null;
        try {
            String query = "select * from CUSTOMERS where CUSTOMER_ID = \""+ customerID + "\";";
            PreparedStatement st = dbConnection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                customer = new Customer();
                customer.setCustomerID(rs.getInt("customer_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setCustomerSurname(rs.getString("customer_surname"));
                customer.setAddress(rs.getString("address"));
                
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }
    
    public static int createOrder(String customerID, String products, String totalPrice) {
        
        int orderNumber = orderCount() + 1;
        
        try {
            String query = "insert into ORDERS values ('" + orderNumber + "', '" + customerID + "', '" + products + "', '" + totalPrice + "');";
            PreparedStatement st = dbConnection.prepareStatement(query);
            st.execute(query);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return orderNumber;
    }

    public static Customer createCustomer(String customerName, String customerSurname, String customerAddress) {
        Customer customer = new Customer();
        int customerID = customerCount() + 1;
        
        customer.setCustomerID(customerID);
        customer.setCustomerName(customerName);
        customer.setCustomerSurname(customerSurname);
        customer.setAddress(customerAddress);
        
        try {
            String query = "insert into CUSTOMERS values ('" + customerID + "', '" + customerName + "', '" + customerSurname + "', '" + customerAddress + "');";
            PreparedStatement st = dbConnection.prepareStatement(query);
            st.execute(query);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public static void updateCustomer(Customer customer) {
        try {
            String query = "update CUSTOMERS set CUSTOMER_NAME = '" + customer.getCustomerName() + 
                            "', CUSTOMER_SURNAME = '" + customer.getCustomerSurname() + 
                            "', ADDRESS = '" + customer.getAddress() + 
                            "' where CUSTOMER_ID = '" + customer.getCustomerID() + "';";
            PreparedStatement st = dbConnection.prepareStatement(query);
            st.execute(query);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

    public static OrderList getOrderHistory(String customerID) {
        OrderList list = new OrderList();
        try {
            String query = "select * from ORDERS where CUSTOMER_ID='" + customerID +"';";
            PreparedStatement st = dbConnection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Order order = new Order();
                order.setOrderID(rs.getInt("order_id"));
                order.setCustomerID(rs.getInt("customer_id"));
                order.setProducts(rs.getString("products"));
                order.setTotalPrice(rs.getInt("total_price"));
                list.add(order);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(WebshopIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
