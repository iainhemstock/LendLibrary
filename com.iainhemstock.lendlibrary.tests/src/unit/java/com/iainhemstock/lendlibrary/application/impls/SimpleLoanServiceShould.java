package com.iainhemstock.lendlibrary.application.impls;

import com.iainhemstock.lendlibrary.application.LoanService;
import com.iainhemstock.lendlibrary.application.LoanServiceShould;

public final class SimpleLoanServiceShould extends LoanServiceShould {
    @Override
    protected LoanService getLoanService() {
        return new SimpleLoanService(super.bookLoanRepository, super.basket);
    }
}
