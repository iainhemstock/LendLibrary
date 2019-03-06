package com.iainhemstock.lendlibrary.infrastructure.persistence.memory;

import com.iainhemstock.lendlibrary.domain.model.member.Member;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberNotFoundException;
import com.iainhemstock.lendlibrary.domain.model.member.MemberRepository;
import com.iainhemstock.lendlibrary.domain.shared.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class MemberRepositoryMemory extends MemberRepository {
    private final List<Member> members = new ArrayList<>();

    @Override
    public boolean contains(final Member member) {
        return members.contains(member);
    }

    @Override
    public void add(final Member member) {
        Member copy = new Member(member);
        members.add(copy);
    }

    @Override
    public void remove(Member member) {
        members.remove(member);
    }

    @Override
    public List<Member> getAll() {
        return members;
    }

    @Override
    public Member getById(final MemberId memberId) {
        return members.stream()
                .filter(member -> member.getId().equals(memberId))
                .findFirst()
                .orElseThrow(getMemberNotFoundException(memberId));
    }

    @Override
    public void update(Member member) {
        int index = IntStream.range(0, members.size())
                .filter(i -> members.get(i).getId().equals(member.getId()))
                .findFirst()
                .orElseThrow(getMemberNotFoundException(member.getId()));

        members.set(index, member);
    }

    private Supplier<MemberNotFoundException> getMemberNotFoundException(MemberId memberId) {
        return () -> new MemberNotFoundException(
                "Member with id <" + memberId.toString() + "> could not be found");
    }
}
