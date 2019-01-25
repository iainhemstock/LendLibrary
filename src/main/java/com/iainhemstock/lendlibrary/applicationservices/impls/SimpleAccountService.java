package com.iainhemstock.lendlibrary.applicationservices.impls;

import com.iainhemstock.lendlibrary.applicationservices.AccountService;
import com.iainhemstock.lendlibrary.domain.model.accounts.Account;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountId;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountRepository;

import java.util.List;

import static java.util.Objects.requireNonNull;

public final class SimpleAccountService implements AccountService {
    private final AccountRepository accountRepository;

    public SimpleAccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        requireNonNull(this.accountRepository, "Account repository is required");
    }

    @Override
    public void openAccount(final Account account) {
        requireNonNull(account, "Account is required");
        accountRepository.add(account);
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
