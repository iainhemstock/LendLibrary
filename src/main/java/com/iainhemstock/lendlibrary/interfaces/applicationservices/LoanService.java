package com.iainhemstock.lendlibrary.interfaces.applicationservices;

import com.iainhemstock.lendlibrary.domain.accounts.valueobjects.AccountId;
import com.iainhemstock.lendlibrary.domain.books.valueobjects.BookId;

public interface LoanService {
    void beginCheckoutFor(AccountId accountId);
    void addToBasket(BookId bookId);
    void confirmCheckout();
}
