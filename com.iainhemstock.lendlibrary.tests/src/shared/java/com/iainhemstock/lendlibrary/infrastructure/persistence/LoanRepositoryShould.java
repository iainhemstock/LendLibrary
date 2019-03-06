package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.application.domain.model.loan.TestLoan;
import com.iainhemstock.lendlibrary.application.domain.model.loan.TestLoan2;
import com.iainhemstock.lendlibrary.application.domain.model.loan.TestLoan3;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.loan.*;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class LoanRepositoryShould extends RepositoryShould {

    protected abstract LoanRepository getRepository();

    @Test
    public void throw_when_fetching_loan_that_does_not_exist() {
        LoanId absentLoanId = new LoanId("id-abcd");
        try {
            getRepository().getById(absentLoanId);
            fail("expected method under test to throw LoanNotFoundExceotion but it didn't");
        } catch (LoanNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("Loan with id <id-abcd> could not be found")));
        }
    }

    @Test
    public void fetch_all_reservations_of_given_member() {
        getRepository().add(new TestLoan());
        getRepository().add(new TestLoan2());
        getRepository().add(new TestLoan3());

        List<Loan> loansByMember = getRepository().findLoansByMember(new MemberId("id-5678"));

        assertThat(loansByMember,
                is(equalTo(List.of(new TestLoan(), new TestLoan3()))));
    }

    @Test
    public void throw_when_updating_loan_that_does_not_exist() {
        Loan absentLoan = new TestLoan();
        try {
            getRepository().update(absentLoan);
            fail("expected method under test to throw LoanNotFoundException but it didn't");
        } catch (LoanNotFoundException ex) {
            assertThat(ex.getMessage(),
                    is(equalTo("Loan with id <" + absentLoan.getId().toString() + "> could not be found")));
        }
    }

    @Override
    public void return_false_when_repo_does_not_contain_item() {
        assertThat(getRepository().contains(new TestLoan()),
                is(equalTo(false)));
    }

    @Override
    public void return_true_when_repo_contains_item() {
        Loan repositoryItem = new TestLoan();
        getRepository().add(repositoryItem);

        assertThat(getRepository().contains(repositoryItem),
                is(equalTo(true)));
    }

    @Override
    public void retrieve_all_items_in_repo() {
        Loan item1 = new TestLoan();
        Loan item2 = new TestLoan2();
        Loan item3 = new TestLoan3();

        getRepository().add(item1);
        getRepository().add(item2);
        getRepository().add(item3);

        assertThat(getRepository().getAll(),
                is(equalTo(List.of(item1, item2, item3))));
    }

    @Override
    public void remove_item_from_repo() {
        Loan item1 = new TestLoan();
        Loan item2 = new TestLoan2();
        Loan item3 = new TestLoan3();

        getRepository().add(item1);
        getRepository().add(item2);
        getRepository().add(item3);

        getRepository().remove(item2);

        assertTrue(getRepository().contains(item1));
        assertFalse("repo should not contain item after removal", getRepository().contains(item2));
        assertTrue(getRepository().contains(item3));
    }
}
