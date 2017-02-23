package app;

import info.AccountInfo;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import beans.AccountManagerRemote;
import beans.BookManagerRemote;
import beans.CartRemote;

public class Client {

    private static final String appName = "LibraryServerSession";
    private static final String moduleName = "LibraryServerSession-ejb";
    private static final String accountName = "AccountManager";
    private static final String accountView = "beans.AccountManagerRemote";
    private static final String bookName = "BookManager";
    private static final String bookView = "beans.BookManagerRemote";
    private static final String cartName = "CartBean";
    private static final String cartView = "beans.CartRemote";
  
    public static void main(String[] args) throws NamingException {
    	  int id=0;
        try {
            // set up for widfly access
        	
            Properties jndiProperties = new Properties();
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            jndiProperties.put("jboss.naming.client.ejb.context", true);

            final Context ctx = new InitialContext(jndiProperties);
            
            // look up accountmanager bean
                    
            AccountManagerRemote am = (AccountManagerRemote) ctx.lookup(appName + "/" + moduleName + "/" + accountName + "!" + accountView);
            BookManagerRemote bm = (BookManagerRemote) ctx.lookup(appName + "/" + moduleName + "/" + bookName + "!" + bookView);
            CartRemote cm = (CartRemote) ctx.lookup(appName + "/" + moduleName + "/" + cartName + "!" + cartView);
            
            boolean cycleOutside=true;
            while(cycleOutside){
            Scanner in = new Scanner(System.in);
            System.out.print("Press:\n"
            		+ "1 to register a new user\n"
            		+ "2 to log in (if you already add some books in a cart the card will be retrived)\n"
            		+ "3 if you want to get a new cart without log in\n"
            		+ "4 if you want to quit\n");
            String answer = in.next(); 
            
            switch(answer){
            case "1": registration(am); break;
            case"2": id=login(am); 
        	if (id==0){
        	 System.out.println("please retry");
        	}else{
        		System.out.println("Welcome "+am.getAccountInfo(id).getUsername());
        		
        		if (cm.getId()==-1){
        			System.out.println("new cart created");
        			cm.initialize(id);}
                else{
                	System.out.print("your cart will be retrived: ");
                	cm.setId(id);
                	 }
               
        		 
           
        		System.out.println("\nBook available:\n");
                listBook(bm);
                System.out.println("\n");
                BookCartCycle(cm,id);
        	}
      
         break;
            case "3":
			cm.initialize();
			System.out.println("\nBook available:\n");
            listBook(bm); 
            BookCartCycle(cm,id);
			break;
            case "4": cycleOutside=false;
            default: System.out.println("Invalid selcetion:\n");break;
                  }}
            
    }catch(Exception e){}

}

public static void registration(AccountManagerRemote am){
	 Scanner in = new Scanner(System.in);
     System.out.print("Inserisci username: ");
     String username = in.next(); 
     System.out.print("Inserisci password: ");
     String password = in.next(); 
     am.registerAccount(username,password);	
}
public static int login(AccountManagerRemote am){
	 Scanner in = new Scanner(System.in);
     System.out.print("Inserisci username: ");
     String username = in.next(); 
     System.out.print("Inserisci password: ");
     String password = in.next(); 
	return am.login(username, password);
	
}


public static void listBook(BookManagerRemote bm){
List <String> books= bm.listBook();
for (int i=0; i<books.size(); i++){
	System.out.println(books.get(i));
}}

public static void addBook(CartRemote cm){
Scanner inBook = new Scanner(System.in);
System.out.print("add the id of the book you want to add ");
String bookid = inBook.next();
cm.addBook(Integer.parseInt(bookid));}


public static void showCart(CartRemote cm){

List <String> books= cm.getContents();
for (int i=0; i<books.size(); i++){
	System.out.println(books.get(i));
}}


public static void BookCartCycle(CartRemote cm,int id){
	boolean cycleInside=true;
	
     while(cycleInside){
    	 System.out.print("Press:\n"
 	     		+ "1 if you want to add a book to the cart\n"
 	     		+ "2 if you want to visualize you cart content\n"
 	     		+ "3 if you want to buy all the contents on the cart\n"
 	     		+ "4 if you want to emptie the cart\n"
 	     		+ "5 if you want to quit\n");
 	 
     Scanner in2 = new Scanner(System.in);
     String answer2 = in2.next();
     
 
     switch(answer2){
     	case "1":addBook(cm); break;
     	case"2":showCart(cm);break;
     	case"3":cm.buy();break;
     	case"4":cm.leave();break;
     	case "5": cycleInside=false;  break;
     	default: System.out.println("Invalid selcetion:\n");id=0;break;
     }}
   	 
    	 
    	 
    	 
    	 
     
	
}




}