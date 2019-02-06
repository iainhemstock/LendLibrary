package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.domain.model.accounts.*;
import com.iainhemstock.lendlibrary.infrastructure.persistence.AccountRepositoryMemory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ITAccountFactoryShould {

    private static final String FIRST_NAME = "Jane";
    private static final String LAST_NAME = "Doe";
    private static final String ADDRESS1 = "1 Ross Avenue";
    private static final String ADDRESS2 = "Levenshulme";
    private static final String CITY = "Manchester";
    private static final String COUNTY = "Greater Manchester";
    private static final String POSTCODE = "M192HW";
    private static final String EMAIL = "janedoe@gmail.com";
    private static final String CONTACT_NUMBER = "01619487013";

    private AccountFactory accountFactory;
    private AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
        accountFactory = new AccountFactory();
        accountRepository = new AccountRepositoryMemory();
    }

    @Test
    public void create_a_new_account() {
        AccountId accountId = accountRepository.nextId();
        Account newAccount = accountFactory.create(
                accountId.toString(),
                FIRST_NAME, LAST_NAME,
                ADDRESS1, ADDRESS2, CITY, COUNTY, POSTCODE,
                CONTACT_NUMBER, EMAIL);

        Account expectedAccount = new Account(accountId, makePersonProfile());
        assertThat(newAccount, is(equalTo(expectedAccount)));
    }

    private PersonProfile makePersonProfile() {
        return new PersonProfile.Builder()
                .withFullName(
                        new FullName.Builder()
                                .withFirstName(new FirstName(FIRST_NAME))
                                .withLastName(new LastName(LAST_NAME))
                            .build())
                .withAddress(
                        new Address.Builder()
                            .withAddress1(new Address1(ADDRESS1))
                            .withAddress2(new Address2(ADDRESS2))
                            .withCity(new City(CITY))
                            .withCounty(new County(COUNTY))
                            .withPostcode(new Postcode(POSTCODE))
                        .build()
                )
                .withContactDetails(
                        new ContactDetails.Builder()
                            .withTelephone(new Telephone(CONTACT_NUMBER))
                            .withEmail(new Email(EMAIL))
                        .build()
                )
            .build();
    }
}
