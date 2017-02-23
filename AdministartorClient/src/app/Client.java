package app;


import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import beans.BookManagerRemote;
import info.BookInfo;

public class Client {

	private static final String appName = "LibraryServerSession";
    private static final String moduleName = "LibraryServerSession-ejb";
    private static final String bookName = "BookManager";
    private static final String bookView = "beans.BookManagerRemote";
   
    
    public static void main(String[] args) throws NamingException {
    	int id=0;
        try {
            // set up for widfly access and look p beans
            Properties jndiProperties = new Properties();
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            jndiProperties.put("jboss.naming.client.ejb.context", true);

            final Context ctx = new InitialContext(jndiProperties);
            
            BookManagerRemote bm = (BookManagerRemote) ctx.lookup(appName + "/" + moduleName + "/" + bookName + "!" + bookView);
            boolean cycle=true;
           //manage user interaction
            while(cycle){
            Scanner in = new Scanner(System.in);
            System.out.print("Press:\n"
            		+ "1 if you want to add a new book\n"
            		+ "2 if you want to list the buying operations\n"
            		+ "3 if you want to quit"
            		);
            String answer = in.next(); 
 
       switch(answer){
        case "1": 
        	Scanner in2 = new Scanner(System.in);
        	System.out.print("insert the admin password:\n");
        	String adminpw = in2.next(); 
        	addB(bm,adminpw); break;
        case "2":
        	Scanner in3 = new Scanner(System.in);
        	System.out.print("insert the admin password:\n");
        	String adminpw2 = in3.next(); 
        	listBuying(bm,adminpw2);break;
        case "3": cycle=false; break;
        default: System.out.println("Invalid selcetion:\n");break;
      
        
        }
            }

    }catch(Exception e){}

}
//call the method addbook on the BookManager bean in order to add a book on the db
public static void addB(BookManagerRemote bm,String pw){
	 Scanner in = new Scanner(System.in);
     System.out.print("Insert title: ");
     String name = in.nextLine(); 
     System.out.print("Insert price(Integer): ");
     int price = Integer.parseInt(in.next()); 
    if( bm.addBook(name, price,pw)){
    System.out.println("book "+name+" added ");
    }else{
    	System.out.println("the admin password is incorrect\n");
    	}	
     }
//call the method listBuying on the BookManagerBeans
public static void listBuying(BookManagerRemote bm,String pw){
List <String> books= bm.listBuying(pw);
for (int i=0; i<books.size(); i++){
	System.out.println(books.get(i));
}}

}