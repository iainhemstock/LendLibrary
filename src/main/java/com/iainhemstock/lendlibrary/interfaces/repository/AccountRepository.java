package com.iainhemstock.lendlibrary.interfaces.repository;

import com.iainhemstock.lendlibrary.domain.accounts.entities.Account;
import com.iainhemstock.lendlibrary.domain.accounts.valueobjects.AccountId;

import java.util.List;

public interface AccountRepository {
    AccountId nextId();
    void add(Account account);
    Account getById(AccountId accountId);
    List<Account> getAll();
    boolean contains(final Account account);
}
