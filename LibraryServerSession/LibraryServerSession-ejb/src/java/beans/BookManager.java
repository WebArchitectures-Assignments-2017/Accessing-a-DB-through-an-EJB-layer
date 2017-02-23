/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Account;
import entities.Book;
import info.BookInfo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author davidelissoni
 */
@Stateless
public class BookManager implements BookManagerRemote {
 @PersistenceContext
    private EntityManager manager;
    static final String ADMIN_PASSWORD="pw";
 
//looking for the info of a book passe as id
    @Override
    public BookInfo getBookInfo(int bookid) {
        Book book = manager.find(Book.class, bookid);
        return book.getBookInfo();
    }
//add a new book, this is an admin operation
    @Override
    public boolean addBook(String name, int price, String pw) {
     if(pw.equals(ADMIN_PASSWORD)){
        Book book= new Book(name,price);
      manager.persist(book);
     return true;}else{return false;}
    }
//list all the book available and return a formatted list
    @Override
    public List <String> listBook() {
Query q= manager.createQuery("SELECT b FROM Book b WHERE b.buyer = ?1");
          q.setParameter(1, 0);
          List <Book> res= q.getResultList();
          List <String> books= new ArrayList<>();
          
  for (int i=0; i<res.size(); i++){
  books.add("id: "+res.get(i).getBookInfo().getId()+" name: "+res.get(i).getBookInfo().getName()+" price: "+res.get(i).getBookInfo().getPrice());
  }
    return books;}
        
   //list the buying operation-> booj+buyer
    @Override
        public List <String> listBuying(String pw) {
    List <String> books= new ArrayList<>();
            if(pw.equals(ADMIN_PASSWORD)){
            Query q= manager.createQuery("SELECT b FROM Book b WHERE b.buyer <> ?1");
          q.setParameter(1, 0);
          
          List <Book> res= q.getResultList();
         
          
  for (int i=0; i<res.size(); i++){
     Account account= manager.find(Account.class, res.get(i).getBookInfo().getBuyer());
     books.add("id: "+res.get(i).getBookInfo().getId()+" name: "+res.get(i).getBookInfo().getName()+" price: "+res.get(i).getBookInfo().getPrice()+" buyer: "+ account.getAccountInfo().getUsername());
  }}else{books.add("the admin password is incorrect");}
    return books;}
    
    
    
    
}
