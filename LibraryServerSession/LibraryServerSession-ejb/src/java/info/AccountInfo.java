
package info;

import java.io.Serializable;

public class AccountInfo implements Serializable {
    
  
    String username,pwd;
    int id;

    public AccountInfo(String username, String pwd,int id) {
       this.username=username;
       this.pwd=pwd;
       this.id=id;
    }

    public String getUsername() {
        return username;
    }

public int getId() {
        return id;
    }


}
