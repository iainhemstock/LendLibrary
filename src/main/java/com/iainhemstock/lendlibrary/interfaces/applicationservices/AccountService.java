package com.iainhemstock.lendlibrary.interfaces.applicationservices;

import com.iainhemstock.lendlibrary.domain.accounts.entities.Account;
import com.iainhemstock.lendlibrary.domain.accounts.valueobjects.AccountId;

import java.util.List;

public interface AccountService {
    void openAccount(Account account);
    List<Account> getAllAccounts();
    Account getAccount(AccountId accountId);
}
