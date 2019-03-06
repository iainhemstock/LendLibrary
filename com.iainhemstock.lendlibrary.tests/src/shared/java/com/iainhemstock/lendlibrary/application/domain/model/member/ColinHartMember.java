package com.iainhemstock.lendlibrary.application.domain.model.member;

import com.iainhemstock.lendlibrary.domain.model.member.*;
import com.iainhemstock.lendlibrary.domain.shared.Id;

public final class ColinHartMember extends Member {

    public ColinHartMember(final String memberId) {
        super(new MemberId(memberId),
                new FullName(
                        new FirstName("Colin"),
                        new LastName("Hart")),
                new Address(
                        new AddressLine1("456 The Avenue"),
                        new AddressLine2("Great Parndon"),
                        new City("Harlow"),
                        new County("Essex"),
                        new Postcode("CM194HG")),
                new ContactDetails(
                        new Telephone("01992967603"),
                        new Email("chart@gmail.com")));
    }

}
