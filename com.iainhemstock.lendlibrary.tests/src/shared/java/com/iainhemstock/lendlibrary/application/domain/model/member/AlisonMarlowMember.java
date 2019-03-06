package com.iainhemstock.lendlibrary.application.domain.model.member;

import com.iainhemstock.lendlibrary.domain.model.member.*;
import com.iainhemstock.lendlibrary.domain.shared.Id;

public final class AlisonMarlowMember extends Member {

    public AlisonMarlowMember(final String memberId) {
        super(new MemberId(memberId),
                new FullName(
                        new FirstName("Alison"),
                        new LastName("Marlow")),
                new Address(
                        new AddressLine1("1 Ross Avenue"),
                        new AddressLine2("Levenshulme"),
                        new City("Manchester"),
                        new County("Greater Manchester"),
                        new Postcode("M192HW")),
                new ContactDetails(
                        new Telephone("01619487013"),
                        new Email("alisonmarlow@gmail.com")));
    }

}
