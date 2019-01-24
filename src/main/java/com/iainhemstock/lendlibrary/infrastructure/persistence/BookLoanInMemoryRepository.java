package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.transactions.entities.BookLoan;
import com.iainhemstock.lendlibrary.domain.transactions.valueobjects.LoanId;
import com.iainhemstock.lendlibrary.interfaces.repository.BookLoanRepository;

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
