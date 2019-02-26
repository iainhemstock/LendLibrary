package com.iainhemstock.lendlibrary.domain.model.member;

import com.iainhemstock.lendlibrary.domain.shared.Id;

public class MemberFactory {

    public Member create(String memberId,
                         String firstName, String lastName,
                         String address1, String address2, String city, String county, String postcode,
                         String contactNumber, String email) {

        FullName fullName = new FullName(
                new FirstName(firstName),
                new LastName(lastName));

        Address address = new Address(
                new AddressLine1(address1),
                new AddressLine2(address2),
                new City(city),
                new County(county),
                new Postcode(postcode));

        ContactDetails contactDetails = new ContactDetails(
                new Telephone(contactNumber),
                new Email(email));

        return new Member(new MemberId(memberId), fullName, address, contactDetails);
    }

}
