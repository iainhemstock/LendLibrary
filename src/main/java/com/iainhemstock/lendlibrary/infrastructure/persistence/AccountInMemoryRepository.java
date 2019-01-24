package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.accounts.entities.Account;
import com.iainhemstock.lendlibrary.domain.accounts.valueobjects.AccountId;
import com.iainhemstock.lendlibrary.interfaces.repository.AccountRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public final class AccountInMemoryRepository implements AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    @Override
    public AccountId nextId() {
        return new AccountId(UUID.randomUUID().toString());
    }

    @Override
    public boolean contains(final Account account) {
        return accounts.contains(account);
    }

    @Override
    public void add(final Account account) {
        accounts.add(account);
    }

    @Override
    public List<Account> getAll() {
        return accounts;
    }

    @Override
    public Account getById(final AccountId accountId) {
        Account ret = null;
        final Iterator<Account> iterator = accounts.iterator();
        while(iterator.hasNext()) {
            Account account = iterator.next();
            if (account.getAccountId().equals(accountId))
                ret = account;
        }
        return ret;
    }
}
