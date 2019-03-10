package com.iainhemstock.lendlibrary.infrastructure.persistence.memory;

import com.iainhemstock.lendlibrary.application.domain.model.loan.TestLoan;
import com.iainhemstock.lendlibrary.domain.model.loan.Loan;
import com.iainhemstock.lendlibrary.domain.model.loan.LoanRepository;
import com.iainhemstock.lendlibrary.infrastructure.persistence.LoanRepositoryShould;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public final class LoanRepositoryMemoryShould extends LoanRepositoryShould {
    private LoanRepository loanRepository = new LoanRepositoryMemory();

    @Override
    public LoanRepository getRepository() {
        return this.loanRepository;
    }

    @Test
    public void store_a_copy_of_loan_rather_than_reference() {
        Loan clientSideLoan = new TestLoan();
        getRepository().add(clientSideLoan);

        assertThat(getRepository().getById(clientSideLoan.getId()),
                is(not(sameInstance(clientSideLoan))));

    }
}
