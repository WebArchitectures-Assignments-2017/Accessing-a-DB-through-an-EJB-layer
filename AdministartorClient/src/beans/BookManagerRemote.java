/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;

import javax.ejb.Remote;

import info.BookInfo;

/**
 *
 * @author davidelissoni
 */
@Remote
public interface BookManagerRemote {
    BookInfo getBookInfo(int bookId);
    boolean addBook(String name, int price,String pw);
    List<String> listBook();
    List<String> listBuying(String pw);
}