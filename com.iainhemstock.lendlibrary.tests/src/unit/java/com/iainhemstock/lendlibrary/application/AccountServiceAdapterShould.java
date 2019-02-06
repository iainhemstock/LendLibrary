package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.application.dto.ExistingAccountDTO;
import com.iainhemstock.lendlibrary.application.dto.NewAccountDTO;
import com.iainhemstock.lendlibrary.application.impls.assembler.ExistingAccountDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.accounts.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public abstract class AccountServiceAdapterShould {

    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @Mock protected AccountFactory accountFactory;
    @Mock protected AccountRepository accountRepository;
    @Mock protected ExistingAccountDTOAssembler existingAccountDTOAssembler;

    protected abstract AccountServiceAdapter getAccountServiceAdapter();
    protected abstract AccountServiceAdapter getAccountServiceAdapterWithNullFactory();
    protected abstract AccountServiceAdapter getAccountServiceAdapterWithNullRepository();
    protected abstract AccountServiceAdapter getAccountServiceAdapterWithNullAssembler();

    @Test
    public void throw_if_adapter_is_initialized_with_null_factory() {
        try {
            getAccountServiceAdapterWithNullFactory();
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Account factory is required")));
        }
    }

    @Test
    public void throw_if_adapter_is_initialized_with_null_repository() {
        try {
            getAccountServiceAdapterWithNullRepository();
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Account repository is required")));
        }
    }

    @Test
    public void throw_if_adapter_is_initialized_with_null_assembler() {
        try {
            getAccountServiceAdapterWithNullAssembler();
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Existing account DTO assembler is required")));
        }
    }

    @Test
    public void save_account_details_in_repo_and_return_account_id_when_opening_new_account() {
        AccountId accountId = Mockito.mock(AccountId.class);
        Account account = Mockito.mock(Account.class);

        when(accountRepository.nextId()).thenReturn(accountId);
        when(accountId.toString()).thenReturn("id-1234");
        when(accountFactory.create(
                "id-1234",
                getAlisonMarlowNewAccountDTO().getFirstName(), getAlisonMarlowNewAccountDTO().getLastName(),
                getAlisonMarlowNewAccountDTO().getAddress1(), getAlisonMarlowNewAccountDTO().getAddress2(),
                getAlisonMarlowNewAccountDTO().getCity(), getAlisonMarlowNewAccountDTO().getCounty(),
                getAlisonMarlowNewAccountDTO().getPostcode(),
                getAlisonMarlowNewAccountDTO().getContactNumber(), getAlisonMarlowNewAccountDTO().getEmail()))
                .thenReturn(account);

        String actualAccountId = getAccountServiceAdapter().openAccount(getAlisonMarlowNewAccountDTO());
        assertThat(actualAccountId, is(equalTo("id-1234")));
        verify(accountRepository).add(account);
    }

    @Test
    public void throw_when_trying_to_fetch_account_with_null_id() {
        try {
            getAccountServiceAdapter().fetchAccount(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Account id is required")));
        }
    }

    @Test
    public void throw_when_trying_to_fetch_account_that_does_not_exist() {
        doThrow(new AccountNotFoundException("Account with id <id-2345> not found"))
                .when(accountRepository).getById(any(AccountId.class));

        String absentAccountId = "id-2345";
        try {
            getAccountServiceAdapter().fetchAccount(absentAccountId);
            fail("expected method under test to throw AccountNotFoundException but it didn't");
        } catch (AccountNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("Account with id <id-2345> not found")));
        }
    }

    @Test
    public void return_details_of_given_account() {
        AccountId accountId = Mockito.mock(AccountId.class);
        Account account = Mockito.mock(Account.class);

        when(accountRepository.nextId()).thenReturn(accountId);
        when(accountRepository.getById(any(AccountId.class))).thenReturn(account);
        when(accountId.toString()).thenReturn("id-1234");
        when(existingAccountDTOAssembler.toDTO(account)).thenReturn(getAlisonMarlowExistingAccountDTO("id-1234"));

        NewAccountDTO alisonMarlowAccountDTO = getAlisonMarlowNewAccountDTO();
        String accountIdStr = getAccountServiceAdapter().openAccount(alisonMarlowAccountDTO);

        ExistingAccountDTO accountDTO = getAccountServiceAdapter().fetchAccount(accountIdStr);

        assertThat(accountDTO.getAccountId(), is(equalTo(accountIdStr)));
        assertThat(accountDTO.getFirstName(), is(equalTo(alisonMarlowAccountDTO.getFirstName())));
        assertThat(accountDTO.getLastName(), is(equalTo(alisonMarlowAccountDTO.getLastName())));
        assertThat(accountDTO.getAddress1(), is(equalTo(alisonMarlowAccountDTO.getAddress1())));
        assertThat(accountDTO.getAddress2(), is(equalTo(alisonMarlowAccountDTO.getAddress2())));
        assertThat(accountDTO.getCity(), is(equalTo(alisonMarlowAccountDTO.getCity())));
        assertThat(accountDTO.getCounty(), is(equalTo(alisonMarlowAccountDTO.getCounty())));
        assertThat(accountDTO.getPostcode(), is(equalTo(alisonMarlowAccountDTO.getPostcode())));
        assertThat(accountDTO.getContactNumber(), is(equalTo(alisonMarlowAccountDTO.getContactNumber())));
        assertThat(accountDTO.getEmail(), is(equalTo(alisonMarlowAccountDTO.getEmail())));
    }

    @Test
    public void return_empty_list_when_trying_to_fetch_accounts_that_dont_exist() {
        assertThat(getAccountServiceAdapter().fetchAllAccounts(), is(equalTo(Collections.EMPTY_LIST)));
    }

    @Test
    public void return_details_of_all_accounts() {
        ExistingAccountDTO alisonMarlowExistingAccountDTO = getAlisonMarlowExistingAccountDTO("id-1234");
        ExistingAccountDTO colinHartExistingAccountDTO = getColinHartExistingAccountDTO("id-5678");

        List<Account> accountsList = List.of(Mockito.mock(Account.class), Mockito.mock(Account.class));
        when(accountRepository.getAll()).thenReturn(accountsList);
        when(existingAccountDTOAssembler.toDTOList(accountsList))
                .thenReturn(List.of(alisonMarlowExistingAccountDTO, colinHartExistingAccountDTO));

        List<ExistingAccountDTO> allFetchedAccountDTOs = getAccountServiceAdapter().fetchAllAccounts();

        verify(accountRepository).getAll();
        verify(existingAccountDTOAssembler).toDTOList(accountsList);
        assertThat(allFetchedAccountDTOs.get(0), is(equalTo(alisonMarlowExistingAccountDTO)));
        assertThat(allFetchedAccountDTOs.get(1), is(equalTo(colinHartExistingAccountDTO)));
    }

    private NewAccountDTO getAlisonMarlowNewAccountDTO() {
        return new NewAccountDTO(
                "Alison", "Marlow",
                "1 Ross Avenue", "Levenshulme", "Manchester", "Greater Manchester", "M192HW",
                "01619487013", "alisonmarlow@gmail.com");
    }

    private ExistingAccountDTO getAlisonMarlowExistingAccountDTO(final String accountId) {
        return new ExistingAccountDTO(
                accountId,
                getAlisonMarlowNewAccountDTO().getFirstName(), getAlisonMarlowNewAccountDTO().getLastName(),
                getAlisonMarlowNewAccountDTO().getAddress1(), getAlisonMarlowNewAccountDTO().getAddress2(),
                getAlisonMarlowNewAccountDTO().getCity(), getAlisonMarlowNewAccountDTO().getCounty(),
                getAlisonMarlowNewAccountDTO().getPostcode(),
                getAlisonMarlowNewAccountDTO().getContactNumber(), getAlisonMarlowNewAccountDTO().getEmail());
    }

    private ExistingAccountDTO getColinHartExistingAccountDTO(String accountId) {
        return new ExistingAccountDTO(
                accountId,
                "John", "Smith",
                "456 The Avenue", "Great Parndon", "Harlow", "Essex", "CM194HG",
                "01992967603", "jsmith@gmail.com");
    }
}
