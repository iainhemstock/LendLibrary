package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.model.member.Membership;
import com.iainhemstock.lendlibrary.domain.model.member.MembershipId;
import com.iainhemstock.lendlibrary.domain.model.member.MembershipRepository;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

public abstract class MembershipRepositoryShould extends RepositoryShould<
        MembershipRepository,
        Membership,
        MembershipId> {

    @Override
    protected Membership makeRepositoryItem() {
        Membership membership = Mockito.mock(Membership.class);
        MembershipId membershipId = Mockito.mock(MembershipId.class);
        when(membership.getId()).thenReturn(membershipId);
        return membership;
    }
}
