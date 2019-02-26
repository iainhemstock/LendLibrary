package com.iainhemstock.lendlibrary.domain.model.member;

import com.iainhemstock.lendlibrary.domain.shared.Repository;

public abstract class MemberRepository extends Repository<Member> {

    public abstract Member getById(final MemberId memberId);

    public MemberId nextId() {
        return new MemberId(super.generateUniqueId());
    }
}
