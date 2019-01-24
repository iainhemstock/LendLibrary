package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.application.SimpleAccountService;
import com.iainhemstock.lendlibrary.domain.accounts.entities.Account;
import com.iainhemstock.lendlibrary.domain.accounts.valueobjects.AccountId;
import com.iainhemstock.lendlibrary.interfaces.repository.RepositoryException;
import com.iainhemstock.lendlibrary.interfaces.repository.AccountRepository;
import com.iainhemstock.lendlibrary.interfaces.applicationservices.AccountService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class SimpleAccountServiceShould {

    @Test
    public void throws_when_initialized_with_null_membership_repo() {
        try {
            new SimpleAccountService(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Account repository is required")));
        }
    }

    @Test
    public void throw_when_attempting_to_open_null_account() {
        AccountRepository accountrepository = Mockito.mock(AccountRepository.class);
        AccountService accountService = new SimpleAccountService(accountrepository);
        try {
            accountService.openAccount(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Account is required")));
        }
    }

    @Test
    public void create_and_store_a_new_membership() {
        Account account = Mockito.mock(Account.class);
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        AccountService accountService = new SimpleAccountService(accountRepository);

        accountService.openAccount(account);

        verify(accountRepository).add(account);
    }

    @Test
    public void rethrow_same_exception_when_repository_throws() {
        RepositoryException mockRepositoryException = Mockito.mock(RepositoryException.class);
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        Account account = Mockito.mock(Account.class);
        AccountService accountService = new SimpleAccountService(accountRepository);
        Mockito.doThrow(mockRepositoryException).when(accountRepository).add(account);

        try {
            accountService.openAccount(account);
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
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        AccountService accountService = new SimpleAccountService(accountRepository);
        when(accountRepository.getAll()).thenReturn(List.of(firstAccount, secondAccount, thirdAccount));
        assertThat(accountService.getAllAccounts(), is(equalTo(List.of(firstAccount, secondAccount, thirdAccount))));
        verify(accountRepository).getAll();
    }

    @Test
    public void throw_when_attempting_to_find_account_with_null_account_id() {
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        AccountService accountService = new SimpleAccountService(accountRepository);
        try {
            accountService.getAccount(null);
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
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        AccountService accountService = new SimpleAccountService(accountRepository);
        when(accountRepository.getById(id)).thenReturn(account);
        assertThat(accountService.getAccount(id), is(equalTo(account)));
    }

    @Test
    public void rethrow_repo_exception_when_account_cannot_be_found() {
        AccountId nonExistentAccountId = Mockito.mock(AccountId.class);
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        RepositoryException repositoryException = Mockito.mock(RepositoryException.class);
        AccountService accountService = new SimpleAccountService(accountRepository);

        when(repositoryException.getMessage()).thenReturn("Account does not exist");
        Mockito.doThrow(repositoryException).when(accountRepository).getById(nonExistentAccountId);

        try {
            accountService.getAccount(nonExistentAccountId);
            fail("expected method under test to throw RepositoryException but it didn't");
        }
        catch (RepositoryException ex) {
            assertThat(ex.getMessage(), is(equalTo("Account does not exist")));
        }
    }
}
