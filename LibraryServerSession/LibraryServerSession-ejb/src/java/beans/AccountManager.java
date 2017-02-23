package beans;

import entities.Account;
import info.AccountInfo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//session bean used to manage entity account
@Stateless
public class AccountManager implements AccountManagerRemote {

    @PersistenceContext
    private EntityManager manager;

//create a new account
    @Override
    public void registerAccount(String usr, String pwd) {
                Account account = new Account(usr,pwd);
                manager.persist(account);
    }
    //get the instance Account info of a specific account
    @Override
      public AccountInfo getAccountInfo(int accountId) {
        Account account = manager.find(Account.class, accountId);
        return account.getAccountInfo();
    }
      
      //check if username and password are correct and then retun its relative id
      @Override
      public int login(String usr,String pwd){
        int id;
      Query q= manager.createQuery("SELECT a FROM Account a WHERE a.username = :user AND a.pwd = :pass");
          q.setParameter("user", usr);
          q.setParameter("pass", pwd);
          List <Account> res= q.getResultList();
            if (res.size()==0)id=0;
            else id=res.get(0).getAccountInfo().getId();
        
      
   return id;
}
  

}
