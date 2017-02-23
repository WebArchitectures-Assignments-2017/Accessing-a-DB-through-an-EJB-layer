/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import info.BookInfo;
import java.io.Serializable;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NEVER;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author davidelissoni
 */
@Entity
public class Book implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookid;
    private String name;
    private int price;
    private int buyer;
    
    public Book(){}
    //constructor for entity Book
    public Book(String name, int price){
     this.name=name;
     this.price=price;
     buyer=0;
   
    }
    //get insttance BookInfo contaning the info of the book
    public BookInfo getBookInfo(){
       
        return new BookInfo(name,price,bookid, buyer);
    }
    //modify the idbuyer when a book is buyed
    public void setBuyer(int buyer){
    
    this.buyer=buyer;
    }
}
