package com.iainhemstock.lendlibrary.infrastructure.persistence.memory;

import com.iainhemstock.lendlibrary.application.domain.model.loan.TestLoan;
import com.iainhemstock.lendlibrary.domain.model.loan.Loan;
import com.iainhemstock.lendlibrary.domain.model.loan.LoanRepository;
import com.iainhemstock.lendlibrary.infrastructure.persistence.LoanRepositoryShould;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public final class LoanRepositoryMemoryShould extends LoanRepositoryShould {
    private LoanRepository loanRepository = new LoanRepositoryMemory();

    @Override
    public LoanRepository getRepository() {
        return this.loanRepository;
    }

    @Test
    public void create_and_store_a_copy_of_loan_rather_than_reference() {
        Loan loan = new TestLoan();

        getRepository().add(loan);

        loan.finalise(new Date());

        assertThat("expected the two loans to have different actual return dates but they were both the same",
                getRepository().getById(loan.getId()),
                is(not(equalTo(loan))));

    }
}
