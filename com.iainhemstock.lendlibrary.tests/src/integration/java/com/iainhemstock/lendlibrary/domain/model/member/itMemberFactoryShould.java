package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.domain.model.member.*;
import com.iainhemstock.lendlibrary.infrastructure.persistence.MemberRepositoryMemory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class itMemberFactoryShould {

    private static final String FIRST_NAME = "Jane";
    private static final String LAST_NAME = "Doe";
    private static final String ADDRESS1 = "1 Ross Avenue";
    private static final String ADDRESS2 = "Levenshulme";
    private static final String CITY = "Manchester";
    private static final String COUNTY = "Greater Manchester";
    private static final String POSTCODE = "M192HW";
    private static final String EMAIL = "janedoe@gmail.com";
    private static final String CONTACT_NUMBER = "01619487013";

    private MemberFactory memberFactory;
    private MemberRepository memberRepository;

    @Before
    public void setUp() throws Exception {
        memberFactory = new MemberFactory();
        memberRepository = new MemberRepositoryMemory();
    }

    @Test
    public void create_a_new_membership() {
        MemberId memberId = memberRepository.nextId();
        Member newMember = memberFactory.create(
                memberId.toString(),
                FIRST_NAME, LAST_NAME,
                ADDRESS1, ADDRESS2, CITY, COUNTY, POSTCODE,
                CONTACT_NUMBER, EMAIL);

        assertThat(newMember.getId(),
                is(equalTo(memberId)));

        assertThat(newMember.getFullName().getFirstName().toString(),
                is(equalTo(FIRST_NAME)));

        assertThat(newMember.getFullName().getLastName().toString(),
                is(equalTo(LAST_NAME)));

        assertThat(newMember.getAddress().getAddressLine1().toString(),
                is(equalTo(ADDRESS1)));

        assertThat(newMember.getAddress().getAddressLine2().toString(),
                is(equalTo(ADDRESS2)));

        assertThat(newMember.getAddress().getCity().toString(),
                is(equalTo(CITY)));

        assertThat(newMember.getAddress().getCounty().toString(),
                is(equalTo(COUNTY)));

        assertThat(newMember.getAddress().getPostcode().toString(),
                is(equalTo(POSTCODE)));

        assertThat(newMember.getContactDetails().getTelephone().toString(),
                is(equalTo(CONTACT_NUMBER)));

        assertThat(newMember.getContactDetails().getEmail().toString(),
                is(equalTo(EMAIL)));
    }
}
