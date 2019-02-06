package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.model.accounts.AccountRepository;
import org.junit.Before;

public final class AccountRepositoryMemoryShould extends AccountRepositoryShould {

    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        accountRepository = new AccountRepositoryMemory();
    }

    @Override
    protected AccountRepository getRepository() {
        return accountRepository;
    }
}
