package account;

import profile.Profile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AccountListManager {
    private HashMap<Long, Account> accountList;

    public AccountListManager(HashMap<Long, Account> accountList) {
        this.accountList = accountList;
    }

    //add an account to the account list
    public void add(Long key, Account account) {
        accountList.put(key, account);
    }

    //remove an account from the account list
    public void remove(Long key) {
        accountList.remove(key);
    }

    //find an account in the linked list based off an account number
    public Account find(Long key) {
        Account account;

        account = accountList.get(key);
        if (account != null) return account;

        System.out.println("Account Not Found");
        return null;
    }

    public void removeAccounts(Profile profile) {
        Iterator iterator = accountList.entrySet().iterator();
        Long key;

        while (iterator.hasNext()){
            Map.Entry mapElement = (Map.Entry)iterator.next();
            key = (Long)mapElement.getKey();

            if (profile.validAccountNumber(key)) {
                iterator.remove(); //removes the HashMap set
            }
        }
    }
}
