/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info;

import java.io.Serializable;

/**
 *
 * @author diegolissoni
 */

public class BookInfo implements Serializable  {
    String name;
    int id,price,buyer; 
    
    public BookInfo(String name, int price,int id,int buyer) {
       this.name=name;
       this.price=price;
       this.id=id;
       this.buyer=buyer;
    }
     public String getName() {
        return name;
    }

public int getId() {
        return id;
    }
public int getPrice() {
        return price;
    }
public int getBuyer() {
        return buyer;
    }
}