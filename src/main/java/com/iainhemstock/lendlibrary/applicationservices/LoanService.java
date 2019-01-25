package com.iainhemstock.lendlibrary.applicationservices;

import com.iainhemstock.lendlibrary.domain.model.accounts.AccountId;
import com.iainhemstock.lendlibrary.domain.model.books.BookId;

public interface LoanService {
    void beginCheckoutFor(AccountId accountId);
    void addToBasket(BookId bookId);
    void confirmCheckout();
}
