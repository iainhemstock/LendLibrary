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

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public final class OpenAccount {

    private ApplicationContext context;

    @Before
    public void setUp() {
        URL resource = getClass().getResource("/beans.xml");
        context = new ClassPathXmlApplicationContext(resource.toString());
    }

    @Test
    public void create_and_save_a_new_account() {
        AccountRepository accountRepository = context.getBean("AccountInMemoryRepository", AccountRepository.class);
        AccountService accountService = context.getBean("SimpleAccountService", AccountService.class);

        AccountId accountId = accountRepository.nextId();
        PersonProfile personProfile = makeTestPersonProfile();
        Account account = new Account(accountId, personProfile);

        accountService.openAccount(account);

        assertThat(accountRepository.contains(account), is(equalTo(true)));
    }

    private PersonProfile makeTestPersonProfile() {
        return new PersonProfile.Builder()
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
    }
}
