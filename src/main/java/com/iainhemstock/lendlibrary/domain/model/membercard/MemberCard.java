package com.iainhemstock.lendlibrary.domain.model.membercard;

import com.iainhemstock.lendlibrary.domain.model.member.FullName;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.shared.Entity;
import com.iainhemstock.lendlibrary.domain.shared.Id;

import java.util.Objects;

public final class MemberCard implements Entity {

    private final MemberId memberId;

    private final FullName fullName;

    public MemberCard(MemberId memberId, FullName fullName) {
        this.memberId = memberId;
        this.fullName = fullName;
    }

    @Override
    public MemberId getId() {
        return this.memberId;
    }

    public FullName getFullName() {
        return this.fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        return Objects.equals(memberId, ((MemberCard) o).memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}
