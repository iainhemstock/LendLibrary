package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.domain.model.accounts.Account;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountId;

import java.util.List;

public interface AccountService {
    AccountId openAccount(Account account);
    List<Account> getAllAccounts();
    Account getAccount(AccountId accountId);
}
