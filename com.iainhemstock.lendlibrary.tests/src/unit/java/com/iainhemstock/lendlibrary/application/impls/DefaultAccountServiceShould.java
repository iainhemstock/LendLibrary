package com.iainhemstock.lendlibrary.application.impls;

import com.iainhemstock.lendlibrary.application.AccountService;
import com.iainhemstock.lendlibrary.application.AccountServiceShould;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountRepository;

public final class DefaultAccountServiceShould extends AccountServiceShould {

    @Override
    public AccountService getAccountService(final AccountRepository accountRepository) {
        return new DefaultAccountService(accountRepository);
    }
}
