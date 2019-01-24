package com.iainhemstock.lendlibrary.application.accounts;

import com.iainhemstock.lendlibrary.domain.accounts.entities.Account;
import com.iainhemstock.lendlibrary.domain.accounts.entities.PersonProfile;
import com.iainhemstock.lendlibrary.domain.accounts.valueobjects.*;
import com.iainhemstock.lendlibrary.interfaces.repository.AccountRepository;
import com.iainhemstock.lendlibrary.interfaces.applicationservices.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class GetAccounts {
    private AccountService accountService;
    private AccountRepository accountRepository;
    private List<Account> accounts;

    @Before
    public void setUp() {
        URL resource = getClass().getResource("/beans.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext(resource.toString());
        accountService = context.getBean("SimpleAccountService", AccountService.class);
        accountRepository = context.getBean("AccountInMemoryRepository", AccountRepository.class);
        accounts = List.of(getFirstAccount(), getSecondAccount(), getThirdAccount());
    }

    @Test
    public void returns_all_accounts_in_repo() {
        addAccountsToAccountRepository();
        assertThat(accountService.getAllAccounts(), is(equalTo(accounts)));
    }

    @Test
    public void return_account_by_id() {
        addAccountsToAccountRepository();
        assertThat(accountService.getAccount(accounts.get(0).getAccountId()), is(equalTo(accounts.get(0))));
        assertThat(accountService.getAccount(accounts.get(1).getAccountId()), is(equalTo(accounts.get(1))));
        assertThat(accountService.getAccount(accounts.get(2).getAccountId()), is(equalTo(accounts.get(2))));
    }

    private void addAccountsToAccountRepository() {
        accountService.openAccount(accounts.get(0));
        accountService.openAccount(accounts.get(1));
        accountService.openAccount(accounts.get(2));
    }

    private Account getFirstAccount() {
        AccountId accountId = accountRepository.nextId();
        PersonProfile personProfile = new PersonProfile.Builder()
                .withFullName(new FullName.Builder()
                        .withFirstName(new FirstName("Jane"))
                        .withLastName(new LastName("Doe"))
                        .build())
                .withAddress(new Address.Builder()
                        .withAddress1(new Address1("123 The Street"))
                        .withAddress2(new Address2("The Town"))
                        .withCity(new City("The City"))
                        .withCounty(new County("The County"))
                        .withPostcode(new Postcode("AB123CD"))
                        .build())
                .withContactDetails(new ContactDetails.Builder()
                        .withTelephone(new Telephone("01234567890"))
                        .withEmail(new Email("janedoe@gmail.com"))
                        .build())
                .build();
        return new Account(accountId, personProfile);
    }

    private Account getSecondAccount() {
        AccountId accountId = accountRepository.nextId();
        PersonProfile personProfile = new PersonProfile.Builder()
                .withFullName(new FullName.Builder()
                        .withFirstName(new FirstName("John"))
                        .withLastName(new LastName("Smith"))
                        .build())
                .withAddress(new Address.Builder()
                        .withAddress1(new Address1("7 Grange Road"))
                        .withAddress2(new Address2("Bracknell"))
                        .withCity(new City("Bracknell Forest"))
                        .withCounty(new County("Berkshire"))
                        .withPostcode(new Postcode("RG122HY"))
                        .build())
                .withContactDetails(new ContactDetails.Builder()
                        .withTelephone(new Telephone("01902667994"))
                        .withEmail(new Email("johnsmith@googlemail.com"))
                        .build())
                .build();
        return new Account(accountId, personProfile);
    }

    private Account getThirdAccount() {
        AccountId accountId = accountRepository.nextId();
        PersonProfile personProfile = new PersonProfile.Builder()
                .withFullName(new FullName.Builder()
                        .withFirstName(new FirstName("Mark"))
                        .withLastName(new LastName("Smith"))
                        .build())
                .withAddress(new Address.Builder()
                        .withAddress1(new Address1("37 Haslingden Drive"))
                        .withAddress2(new Address2(""))
                        .withCity(new City("Bradford"))
                        .withCounty(new County("West Yorkshire"))
                        .withPostcode(new Postcode("BD95HT"))
                        .build())
                .withContactDetails(new ContactDetails.Builder()
                        .withTelephone(new Telephone("0127468635"))
                        .withEmail(new Email("marksmith@gmail.com"))
                        .build())
                .build();
        return new Account(accountId, personProfile);
    }
}
