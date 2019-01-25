package com.iainhemstock.lendlibrary.domain.model.accounts;

import java.util.List;

public interface AccountRepository {
    AccountId nextId();
    void add(Account account);
    Account getById(AccountId accountId);
    List<Account> getAll();
    boolean contains(final Account account);
}
