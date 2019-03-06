package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.application.domain.model.member.AlisonMarlowMember;
import com.iainhemstock.lendlibrary.application.domain.model.member.ColinHartMember;
import com.iainhemstock.lendlibrary.application.domain.model.member.EricFordMember;
import com.iainhemstock.lendlibrary.domain.model.member.*;
import com.iainhemstock.lendlibrary.domain.shared.Id;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class MemberRepositoryShould extends RepositoryShould {

    protected abstract MemberRepository getRepository();

    @Test
    public void update_member() {
        ColinHartMember member = new ColinHartMember("id-1234");
        getRepository().add(member);

        FullName updatedName = new FullName(new FirstName("Graham"), new LastName("Jones"));
        member.renameTo(updatedName);
        getRepository().update(member);
        Member modifiedMember = getRepository().getById(member.getId());

        assertThat(member.getId(), is(equalTo(modifiedMember.getId())));
        assertThat(modifiedMember.getFullName().getFirstName().toString(), is(equalTo("Graham")));
        assertThat(modifiedMember.getFullName().getLastName().toString(), is(equalTo("Jones")));
    }

    @Test
    public void throw_when_fetching_member_that_does_not_exist() {
        MemberId nonExistentId = new MemberId("id-1234");
        try {
            getRepository().getById(nonExistentId);
            fail("expected method under test to throw MemberNotFoundException but it didn't");
        } catch (MemberNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member with id <id-1234> could not be found")));
        }
    }

    @Test
    public void throw_when_updating_member_that_does_not_exist_in_repo() {
        Member nonExistentMember = new ColinHartMember("id-1234");
        try {
            getRepository().update(nonExistentMember);
            fail("expected method under test to throw MemberNonFoundException but it didn't");
        } catch (MemberNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member with id <id-1234> could not be found")));
        }
    }

    @Test
    public void retrieve_member_by_id() {
        Member expectedMember = new ColinHartMember("id-5678");
        getRepository().add(expectedMember);

        Member actualItem = getRepository().getById(expectedMember.getId());

        assertThat(actualItem, is(equalTo(expectedMember)));
    }

    @Override
    public void return_false_when_repo_does_not_contain_item() {
        assertThat(getRepository().contains(new ColinHartMember("id-5678")),
                is(equalTo(false)));
    }

    @Override
    public void return_true_when_repo_contains_item() {
        Member repositoryItem = new ColinHartMember("id-5678");
        getRepository().add(repositoryItem);

        assertThat(getRepository().contains(repositoryItem),
                is(equalTo(true)));
    }

    @Override
    public void retrieve_all_items_in_repo() {
        Member item1 = new ColinHartMember("id-5678");
        Member item2 = new AlisonMarlowMember("id-6789");
        Member item3 = new EricFordMember("id-7890");

        getRepository().add(item1);
        getRepository().add(item2);
        getRepository().add(item3);

        assertThat(getRepository().getAll(),
                is(equalTo(List.of(item1, item2, item3))));
    }

    @Override
    public void remove_item_from_repo() {
        Member item1 = new ColinHartMember("id-1234");
        Member item2 = new AlisonMarlowMember("id-2345");
        Member item3 = new EricFordMember("id-3456");

        getRepository().add(item1);
        getRepository().add(item2);
        getRepository().add(item3);

        getRepository().remove(item2);

        assertTrue(getRepository().contains(item1));
        assertFalse("repo should not contain item after removal", getRepository().contains(item2));
        assertTrue(getRepository().contains(item3));
    }
}
