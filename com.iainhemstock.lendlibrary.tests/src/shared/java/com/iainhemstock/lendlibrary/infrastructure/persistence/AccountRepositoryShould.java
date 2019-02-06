package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.model.accounts.Account;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountId;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountRepository;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

public abstract class AccountRepositoryShould extends RepositoryShould<
        AccountRepository,
        Account,
        AccountId> {

    @Override
    protected Account makeRepositoryItem() {
        Account account = Mockito.mock(Account.class);
        AccountId accountId = Mockito.mock(AccountId.class);
        when(account.getId()).thenReturn(accountId);
        return account;
    }
}
