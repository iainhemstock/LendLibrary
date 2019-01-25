package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.model.transactions.BookLoan;
import com.iainhemstock.lendlibrary.domain.model.transactions.LoanId;
import com.iainhemstock.lendlibrary.domain.model.transactions.BookLoanRepository;

public class BookLoanInMemoryRepository implements BookLoanRepository {
    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(final BookLoan loan) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LoanId nextId() {
        throw new UnsupportedOperationException();
    }
}
