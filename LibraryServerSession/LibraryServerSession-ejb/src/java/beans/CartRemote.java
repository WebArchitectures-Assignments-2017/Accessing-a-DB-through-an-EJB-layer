package beans;

import java.util.*;
import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import javax.ejb.Remote;

@Remote
public interface CartRemote {
  public void initialize(int id);
   public void addBook(int idbook);
   public List<String> getContents();
   public int getId();
   public void setId(int id);
   public void initialize();
   public void leave();
   public void buy();
}