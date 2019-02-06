package com.iainhemstock.lendlibrary.application.impls.assembler;

import com.iainhemstock.lendlibrary.application.dto.AccountDTO;
import com.iainhemstock.lendlibrary.domain.model.accounts.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ITAccountDTOAssemblerShould {

    private static final String ACC1_ACCOUNT_ID = "9ba0d1ad-cb44-4ca4-bb0b-a0b9cab13d97";
    private static final String ACC1_FIRST_NAME = "Jane";
    private static final String ACC1_LAST_NAME = "Doe";
    private static final String ACC1_ADDRESS1 = "1 Ross Avenue";
    private static final String ACC1_ADDRESS2 = "Levenshulme";
    private static final String ACC1_CITY = "Manchester";
    private static final String ACC1_COUNTY = "Greater Manchester";
    private static final String ACC1_POSTCODE = "M192HW";
    private static final String ACC1_EMAIL = "janedoe@gmail.com";
    private static final String ACC1_CONTACT_NUMBER = "01619487013";

    private static final String ACC2_ACCOUNT_ID = "e9a08de2-cab0-4a16-ba2e-1fc5c58664e7";
    private static final String ACC2_FIRST_NAME = "John";
    private static final String ACC2_LAST_NAME = "Smith";
    private static final String ACC2_ADDRESS1 = "456 The Avenue";
    private static final String ACC2_ADDRESS2 = "Great Parndon";
    private static final String ACC2_CITY = "Harlow";
    private static final String ACC2_COUNTY = "Essex";
    private static final String ACC2_POSTCODE = "CM194HG";
    private static final String ACC2_CONTACT_NUMBER = "01992967603";
    private static final String ACC2_EMAIL = "jsmith@gmail.com";

    private AccountDTOAssembler assembler;

    @Before
    public void setUp() throws Exception {
        assembler = new AccountDTOAssembler();
    }

    @Test
    public void return_empty_list_when_no_accounts_exist() {
        List<Account> emptyAccountsList = new ArrayList<>();
        List<AccountDTO> actualDTOs = assembler.toDTOList(emptyAccountsList);
        List<AccountDTO> expectedDTOs = new ArrayList<>();

        assertThat(actualDTOs, is(equalTo(expectedDTOs)));
    }

    @Test
    public void return_list_of_accounts_converted_to_dtos() {
        List<Account> accounts = makeAccounts();
        List<AccountDTO> expectedDTOs = makeExpectedDTOs();
        List<AccountDTO> actualDTOs = assembler.toDTOList(accounts);
        assertThat(actualDTOs, is(equalTo(expectedDTOs)));
    }

    @Test
    public void return_account_converted_to_dto() {
        Account alisonMarlowAccount = getAlisonMarlowAccount();

        AccountDTO accountDTO = assembler.toDTO(alisonMarlowAccount);

        FullName fullName = alisonMarlowAccount.getPersonProfile().getFullName();
        Address address = alisonMarlowAccount.getPersonProfile().getAddress();
        ContactDetails contactDetails = alisonMarlowAccount.getPersonProfile().getContactDetails();

        assertThat(accountDTO.getAccountId(), is(equalTo(alisonMarlowAccount.getId().toString())));
        assertThat(accountDTO.getFirstName(), is(equalTo(fullName.getFirstName().toString())));
        assertThat(accountDTO.getLastName(), is(equalTo(fullName.getLastName().toString())));
        assertThat(accountDTO.getAddress1(), is(equalTo(address.getAddress1().toString())));
        assertThat(accountDTO.getAddress2(), is(equalTo(address.getAddress2().toString())));
        assertThat(accountDTO.getCity(), is(equalTo(address.getCity().toString())));
        assertThat(accountDTO.getCounty(), is(equalTo(address.getCounty().toString())));
        assertThat(accountDTO.getPostcode(), is(equalTo(address.getPostcode().toString())));
        assertThat(accountDTO.getContactNumber(), is(equalTo(contactDetails.getTelephone().toString())));
        assertThat(accountDTO.getEmail(), is(equalTo(contactDetails.getEmail().toString())));
    }

    private Account getAlisonMarlowAccount() {
        return new AccountFactory().create(
                "id-1234",
                "Alison", "Marlow",
                "1 Ross Avenue", "Levenshulme", "Manchester", "Greater Manchester", "M192HW",
                "01619487013", "alisonmarlow@gmail.com");
    }

    private List<AccountDTO> makeExpectedDTOs() {
        return List.of(
                new AccountDTO(
                        ACC1_ACCOUNT_ID,
                        ACC1_FIRST_NAME, ACC1_LAST_NAME,
                        ACC1_ADDRESS1, ACC1_ADDRESS2, ACC1_CITY, ACC1_COUNTY, ACC1_POSTCODE,
                        ACC1_CONTACT_NUMBER, ACC1_EMAIL),
                new AccountDTO(
                        ACC2_ACCOUNT_ID,
                        ACC2_FIRST_NAME, ACC2_LAST_NAME,
                        ACC2_ADDRESS1, ACC2_ADDRESS2, ACC2_CITY, ACC2_COUNTY, ACC2_POSTCODE,
                        ACC2_CONTACT_NUMBER, ACC2_EMAIL));
    }

    private List<Account> makeAccounts() {
        return List.of(
                new AccountFactory().create(
                    ACC1_ACCOUNT_ID,
                    ACC1_FIRST_NAME, ACC1_LAST_NAME,
                    ACC1_ADDRESS1, ACC1_ADDRESS2, ACC1_CITY, ACC1_COUNTY, ACC1_POSTCODE,
                    ACC1_CONTACT_NUMBER, ACC1_EMAIL),
                new AccountFactory().create(
                    ACC2_ACCOUNT_ID,
                    ACC2_FIRST_NAME, ACC2_LAST_NAME,
                    ACC2_ADDRESS1, ACC2_ADDRESS2, ACC2_CITY, ACC2_COUNTY, ACC2_POSTCODE,
                    ACC2_CONTACT_NUMBER, ACC2_EMAIL));
    }
}
