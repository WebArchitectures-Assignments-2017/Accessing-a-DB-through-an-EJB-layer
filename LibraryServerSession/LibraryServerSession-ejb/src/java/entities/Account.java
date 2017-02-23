
package entities;


import info.AccountInfo;
import java.io.Serializable;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NEVER;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Account implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    private String username;
    private String pwd;
  
    public Account(){}
    //account constructor
    public Account(String usr, String pwd){
     this.username=usr;
     this.pwd=pwd;
    }
    
 //get new Accountinfo instance
      public AccountInfo getAccountInfo(){
        return new AccountInfo(username,pwd,accountId);
    }


}
