package com.iainhemstock.lendlibrary.domain.model.accounts;

public class AccountFactory {

    public Account create(String accountId,
                          String firstName, String lastName,
                          String address1, String address2, String city, String county, String postcode,
                          String contactNumber, String email) {

        FullName fullName = new FullName.Builder()
                .withFirstName(new FirstName(firstName))
                .withLastName(new LastName(lastName))
                .build();
        Address address = new Address.Builder()
                .withAddress1(new Address1(address1))
                .withAddress2(new Address2(address2))
                .withCity(new City(city))
                .withCounty(new County(county))
                .withPostcode(new Postcode(postcode))
                .build();
        ContactDetails contactDetails = new ContactDetails.Builder()
                .withTelephone(new Telephone(contactNumber))
                .withEmail(new Email(email))
                .build();
        PersonProfile personProfile = new PersonProfile.Builder()
                .withFullName(fullName)
                .withAddress(address)
                .withContactDetails(contactDetails)
                .build();

        return new Account(new AccountId(accountId), personProfile);
    }

}
