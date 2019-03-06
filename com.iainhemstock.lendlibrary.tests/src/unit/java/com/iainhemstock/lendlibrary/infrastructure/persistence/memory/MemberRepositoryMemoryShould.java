package com.iainhemstock.lendlibrary.infrastructure.persistence.memory;

import com.iainhemstock.lendlibrary.application.domain.model.member.AlisonMarlowMember;
import com.iainhemstock.lendlibrary.application.domain.model.member.ColinHartMember;
import com.iainhemstock.lendlibrary.domain.model.member.Address;
import com.iainhemstock.lendlibrary.domain.model.member.Member;
import com.iainhemstock.lendlibrary.domain.model.member.MemberRepository;
import com.iainhemstock.lendlibrary.infrastructure.persistence.MemberRepositoryShould;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

public final class MemberRepositoryMemoryShould extends MemberRepositoryShould {

    private MemberRepository memberRepository = new MemberRepositoryMemory();

    @Override
    protected MemberRepository getRepository() {
        return memberRepository;
    }

    @Test
    public void create_and_store_a_copy_of_member_rather_than_reference() {
        Member member = new ColinHartMember("id-1234");

        getRepository().add(member);

        Address relocatedAddress = new AlisonMarlowMember("id-5678").getAddress();
        member.relocateTo(relocatedAddress);

        assertThat("expected the two members to have different addresses but they were both the same",
                getRepository().getById(member.getId()),
                is(not(equalTo(member))));

    }
}
