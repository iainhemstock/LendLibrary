package com.iainhemstock.lendlibrary.domain.model.transactions;

public interface BookLoanRepository {
    LoanId nextId();
    int size();
    boolean contains(BookLoan loan);
}
