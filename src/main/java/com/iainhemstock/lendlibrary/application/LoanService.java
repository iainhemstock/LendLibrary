package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.domain.model.accounts.AccountId;
import com.iainhemstock.lendlibrary.domain.model.catalogue.BookId;

public interface LoanService {
    void beginCheckoutFor(AccountId accountId);
    void addToBasket(BookId bookId);
    void confirmCheckout();
}
