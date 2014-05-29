/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.ejb.Stateless;

/**
 *
 * @author HP
 */
@Stateless
public class Product {
    private String name;
    private String components;
    private int price;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Product() {
        name = "";
        components = "";
        price = 0;
    }
    public Product(String name, String components, int price){
        this.name = name;
        this.components = components;
        this.price = price;
    }
    
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public String getComponents(){
        return components;
    }
    public void setComponents(String components){
        this.components = components;
    }
    
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }
}
