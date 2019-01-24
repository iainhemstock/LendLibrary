package com.iainhemstock.lendlibrary.interfaces.repository;

import com.iainhemstock.lendlibrary.domain.transactions.entities.BookLoan;
import com.iainhemstock.lendlibrary.domain.transactions.valueobjects.LoanId;

public interface BookLoanRepository {
    LoanId nextId();
    int size();
    boolean contains(BookLoan loan);
}
