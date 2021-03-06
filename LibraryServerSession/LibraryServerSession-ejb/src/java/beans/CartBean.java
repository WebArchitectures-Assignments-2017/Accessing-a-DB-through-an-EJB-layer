/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;



import entities.Book;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;


@Stateful
public class CartBean implements CartRemote {

    int customerId=-1;
    List<Book> contents;
    @PersistenceContext
    private EntityManager manager;
   //constructor for a logged person
 public void initialize(int id){
     customerId=id;
     contents = new ArrayList<Book>();
    }

 @Override
 public int getId(){
 return customerId; 
}
 //add a book on the cart
@Override
    public void addBook(int idbook) {
    Book book=manager.find(Book.class, idbook);
        contents.add(book);
        
    }
//return a string list containg the list of books present in the cart
 @Override
    public List<String> getContents() {
       List <String> books= new ArrayList<>();
        if (contents.size()==0){books.add("there is no books in this cart");}else{
        
        for ( int i=0;i<contents.size();i++){
        books.add("id: "+contents.get(i).getBookInfo().getId()+" name: "+contents.get(i).getBookInfo().getName()+" price: "+contents.get(i).getBookInfo().getPrice());
        
        }
        }
        
        return books;
    }
    
    //transaction that set the idbuyer of every book element present in the cart and then clear the cart 
    @Override
    public void buy(){
    for (Book book: contents){
       
    book.setBuyer(customerId);
    manager.merge(book);
    }
    contents.clear();
    }
    
    
//clear the cart
@Override
    public void leave() {
        contents.clear();
    }

//used when an unlogged person get a cart and then do the login retrive the cart
    @Override
    public void setId(int id) {
        this.customerId=id;
    }
//constructor for unlogged user
    @Override
    public void initialize() {
        contents = new ArrayList<Book>();
     customerId=0;
    }
}
