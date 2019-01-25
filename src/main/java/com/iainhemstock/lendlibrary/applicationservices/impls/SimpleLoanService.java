package com.iainhemstock.lendlibrary.applicationservices.impls;

import com.iainhemstock.lendlibrary.applicationservices.LoanService;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountId;
import com.iainhemstock.lendlibrary.domain.model.books.BookId;
import com.iainhemstock.lendlibrary.domain.model.transactions.Basket;
import com.iainhemstock.lendlibrary.domain.model.transactions.BookLoanRepository;

import static java.util.Objects.requireNonNull;

public final class SimpleLoanService implements LoanService {
    private BookLoanRepository bookLoanRepository;
    private Basket basket;

    public SimpleLoanService(final BookLoanRepository bookLoanRepository, final Basket basket) {
        this.bookLoanRepository = bookLoanRepository;
        this.basket = basket;
    }

    @Override
    public void beginCheckoutFor(final AccountId accountId) {
        requireNonNull(accountId, "Account Id is required");
        basket.forAccount(accountId);
    }

    @Override
    public void addToBasket(final BookId bookId) {
        requireNonNull(bookId, "Book Id is required");
        basket.addBook(bookId);
    }

    @Override
    public void confirmCheckout() {
        throw new UnsupportedOperationException();
    }
}
