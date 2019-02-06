package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.model.accounts.Account;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountId;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class AccountRepositoryMemory extends AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

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
            if (account.getId().equals(accountId))
                ret = account;
        }
        return ret;
    }
}
