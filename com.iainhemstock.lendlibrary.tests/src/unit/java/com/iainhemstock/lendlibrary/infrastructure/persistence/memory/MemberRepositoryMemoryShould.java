package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.model.member.MemberRepository;
import org.junit.Before;

public final class MemberRepositoryMemoryShould extends MemberRepositoryShould {

    private MemberRepository memberRepository;

    @Before
    public void setUp() {
        memberRepository = new MemberRepositoryMemory();
    }

    @Override
    protected MemberRepository getRepository() {
        return memberRepository;
    }
}
