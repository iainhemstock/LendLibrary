package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.application.dto.AccountDTO;
import com.iainhemstock.lendlibrary.application.impls.DefaultAccountServiceAdapter;
import com.iainhemstock.lendlibrary.application.impls.assembler.AccountDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountFactory;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountId;
import com.iainhemstock.lendlibrary.infrastructure.persistence.AccountRepositoryMemory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ITAccountServiceAdapterShould {

    private AccountServiceAdapter accountServiceAdapter;
    private TestableAccountRepository testableAccountRepository;

    @Before
    public void setUp() {
        testableAccountRepository = new TestableAccountRepository();
        AccountFactory accountFactory = new AccountFactory();
        AccountDTOAssembler accountDTOAssembler = new AccountDTOAssembler();
        accountServiceAdapter = new DefaultAccountServiceAdapter(testableAccountRepository, accountFactory, accountDTOAssembler);
    }

    @Test
    public void open_new_account() {
        final AccountDTO alisonMarlowAccountDTO = getAlisonMarlowNewAccountDTO();

        final String alisonMarlowAccountId = accountServiceAdapter.openAccount(alisonMarlowAccountDTO);
        final AccountDTO fetchedAccountDTO = accountServiceAdapter.fetchAccount(alisonMarlowAccountId);
        final List<AccountDTO> allFetchedAccountDTOs = accountServiceAdapter.fetchAllAccounts();

        assertThat(alisonMarlowAccountId, is(equalTo(testableAccountRepository.getLastId().toString())));
        assertThat(fetchedAccountDTO.getAccountId(), is(equalTo(alisonMarlowAccountId)));
        assertThat(fetchedAccountDTO.getFirstName(), is(equalTo(alisonMarlowAccountDTO.getFirstName())));
        assertThat(fetchedAccountDTO.getLastName(), is(equalTo(alisonMarlowAccountDTO.getLastName())));
        assertThat(fetchedAccountDTO.getAddress1(), is(equalTo(alisonMarlowAccountDTO.getAddress1())));
        assertThat(fetchedAccountDTO.getAddress2(), is(equalTo(alisonMarlowAccountDTO.getAddress2())));
        assertThat(fetchedAccountDTO.getCity(), is(equalTo(alisonMarlowAccountDTO.getCity())));
        assertThat(fetchedAccountDTO.getCounty(), is(equalTo(alisonMarlowAccountDTO.getCounty())));
        assertThat(fetchedAccountDTO.getPostcode(), is(equalTo(alisonMarlowAccountDTO.getPostcode())));
        assertThat(fetchedAccountDTO.getContactNumber(), is(equalTo(alisonMarlowAccountDTO.getContactNumber())));
        assertThat(fetchedAccountDTO.getEmail(), is(equalTo(alisonMarlowAccountDTO.getEmail())));

        assertThat(allFetchedAccountDTOs.get(0), is(equalTo(fetchedAccountDTO)));
    }

    private AccountDTO getAlisonMarlowNewAccountDTO() {
        return new AccountDTO(null,
                "Alison", "Marlow",
                "1 Ross Avenue", "Levenshulme", "Manchester", "Greater Manchester", "M192HW",
                "01619487013", "alisonmarlow@gmail.com");
    }

    private class TestableAccountRepository extends AccountRepositoryMemory {
        private AccountId accountId;

        @Override
        public AccountId nextId() {
            accountId = new AccountId(UUID.randomUUID().toString().replace("-", ""));
            return accountId;
        }

        public AccountId getLastId() {
            return accountId;
        }
    }
}
