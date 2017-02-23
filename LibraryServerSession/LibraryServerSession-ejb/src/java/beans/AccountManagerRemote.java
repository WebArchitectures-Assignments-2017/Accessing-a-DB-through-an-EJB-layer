package beans;

import info.AccountInfo;
import javax.ejb.Remote;

@Remote
public interface AccountManagerRemote {

    
 AccountInfo getAccountInfo(int accountId);
    void registerAccount(String usr, String pwd);
int login(String usr,String pwd);

    
}
