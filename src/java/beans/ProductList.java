/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.ejb.Stateless;
import beans.Product;
/**
 *
 * @author HP
 */
@Stateless
public class ProductList {
    private Product[] list;
    private int index = 0;
    private int size = 0;

    public ProductList() {
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public ProductList(int size){
        list = new Product[size];
        this.size = size;
    }
    
    public void add(Product product){
        list[index] = product;
        index++;
    }
    
    public Product getProduct(int i){
        return list[i];
    }
    
    public int getSize(){
        return size;
    }
}
