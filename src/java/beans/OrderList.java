/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.ArrayList;

/**
 *
 * @author deha
 */
public class OrderList {
    private ArrayList<Order> list;

    public OrderList() {
        list = new ArrayList<Order>();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public OrderList(int size){
        list = new ArrayList<Order>(size);
    }
    
    public void add(Order order){
        list.add(order);
    }
    
    public Order getOrder(int i){
        return list.get(i);
    }
    
    public int getSize(){
        return list.size();
    }
}
