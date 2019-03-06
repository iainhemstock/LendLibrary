package com.iainhemstock.lendlibrary.application.domain.model.member;

import com.iainhemstock.lendlibrary.domain.model.member.*;

public final class EricFordMember extends Member {

    public EricFordMember(final String memberId) {
        super(new MemberId(memberId),
                new FullName(
                        new FirstName("Eric"),
                        new LastName("Ford")),
                new Address(
                        new AddressLine1("5 The Close"),
                        new AddressLine2("Halesowen"),
                        new City("Cradley"),
                        new County("West Midlands"),
                        new Postcode("B632SJ")),
                new ContactDetails(
                        new Telephone("01384598391"),
                        new Email("ericford@gmail.com")));
    }

}
