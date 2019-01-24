package com.iainhemstock.lendlibrary.domain.transactions.entities;

import com.iainhemstock.lendlibrary.domain.accounts.valueobjects.AccountId;
import com.iainhemstock.lendlibrary.domain.books.valueobjects.BookId;
import com.iainhemstock.lendlibrary.domain.transactions.valueobjects.LoanId;
import com.iainhemstock.lendlibrary.domain.transactions.valueobjects.RentalPeriod;

import java.util.Date;

public final class BookLoan {

    public BookLoan(final LoanId loanId,
                    final AccountId accountId,
                    final BookId bookId,
                    final RentalPeriod rentalPeriod) {

    }
}
