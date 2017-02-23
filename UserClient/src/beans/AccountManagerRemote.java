package beans;

import javax.ejb.Remote;

import info.AccountInfo;

@Remote
public interface AccountManagerRemote {
    AccountInfo getAccountInfo(int accountId);
    void registerAccount(String usr, String pwd); 
    int login(String usr,String pwd);
}
