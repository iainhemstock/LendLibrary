package com.iainhemstock.lendlibrary.application.impls;

import com.iainhemstock.lendlibrary.application.AccountService;
import com.iainhemstock.lendlibrary.application.ApplicationEvents;
import com.iainhemstock.lendlibrary.domain.model.accounts.Account;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountId;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountRepository;

import java.util.List;

import static java.util.Objects.requireNonNull;

public final class DefaultAccountService implements AccountService {
    private final AccountRepository accountRepository;

    public DefaultAccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        requireNonNull(this.accountRepository, "Account repository is required");
    }

    @Override
    public AccountId openAccount(final Account account) {
        requireNonNull(account, "Account is required");
        accountRepository.add(account);
//        domainEvents.raise(new AccountWasCreatedEvent(account));
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.getAll();
    }

    @Override
    public Account getAccount(final AccountId accountId) {
        requireNonNull(accountId, "Account Id is required");
        return accountRepository.getById(accountId);
    }
}
