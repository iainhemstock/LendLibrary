package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.domain.model.accounts.Account;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountId;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountRepository;
import com.iainhemstock.lendlibrary.domain.shared.RepositoryException;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public abstract class AccountServiceShould {

    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @Mock protected AccountRepository accountRepository;

    protected abstract AccountService getAccountService(final AccountRepository accountRepository);

    @Test
    public void throws_when_initialized_with_null_account_repo() {
        try {
            getAccountService(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Account repository is required")));
        }
    }

    @Test
    public void throw_when_attempting_to_open_null_account() {
        try {
            getAccountService(accountRepository).openAccount(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Account is required")));
        }
    }

    @Test
    public void create_and_store_a_new_account() {
        Account account = Mockito.mock(Account.class);
        getAccountService(accountRepository).openAccount(account);
        verify(accountRepository).add(account);
    }

    @Test
    public void rethrow_same_exception_when_repository_throws() {
        RepositoryException mockRepositoryException = Mockito.mock(RepositoryException.class);
        Account account = Mockito.mock(Account.class);
        Mockito.doThrow(mockRepositoryException).when(accountRepository).add(account);

        try {
            getAccountService(accountRepository).openAccount(account);
            fail("expected method under test to throw RepositoryException but it didn't");
        }
        catch (RepositoryException caughtException) {
            assertThat(caughtException, is(equalTo(mockRepositoryException)));
        }
    }

    @Test
    public void return_all_accounts_in_repo() {
        final Account firstAccount = Mockito.mock(Account.class);
        final Account secondAccount = Mockito.mock(Account.class);
        final Account thirdAccount = Mockito.mock(Account.class);
        when(accountRepository.getAll()).thenReturn(List.of(firstAccount, secondAccount, thirdAccount));

        assertThat(
                getAccountService(accountRepository).getAllAccounts(),
                is(equalTo(List.of(firstAccount, secondAccount, thirdAccount))));
        verify(accountRepository).getAll();
    }

    @Test
    public void throw_when_attempting_to_find_account_with_null_account_id() {
        try {
            getAccountService(accountRepository).getAccount(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Account Id is required")));
        }
    }

    @Test
    public void return_account_found_by_id() {
        Account account = Mockito.mock(Account.class);
        AccountId id = Mockito.mock(AccountId.class);
        when(accountRepository.getById(id)).thenReturn(account);

        assertThat(getAccountService(accountRepository).getAccount(id), is(equalTo(account)));
    }

    @Test
    public void rethrow_repo_exception_when_account_cannot_be_found() {
        AccountId nonExistentAccountId = Mockito.mock(AccountId.class);
        RepositoryException repositoryException = Mockito.mock(RepositoryException.class);

        when(repositoryException.getMessage()).thenReturn("Account does not exist");
        Mockito.doThrow(repositoryException).when(accountRepository).getById(nonExistentAccountId);

        try {
            getAccountService(accountRepository).getAccount(nonExistentAccountId);
            fail("expected method under test to throw RepositoryException but it didn't");
        }
        catch (RepositoryException ex) {
            assertThat(ex.getMessage(), is(equalTo("Account does not exist")));
        }
    }
}
