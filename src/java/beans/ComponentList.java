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
public class ComponentList {
    private Component[] list;
    private int size;
    private int index;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public ComponentList() {
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public ComponentList(int size){
        list = new Component[size];
        this.size = size;
    }
    
    public void add(Component c){
        list[index] = c;
        index++;
    }
    
    public Component getComponent(int i){
        return list[i];
    }
    
    public int getSize(){
        return size;
    }
}
