package com.iainhemstock.lendlibrary.domain.transactions.valueobjects;

import com.iainhemstock.lendlibrary.domain.accounts.valueobjects.AccountId;
import com.iainhemstock.lendlibrary.domain.books.valueobjects.BookId;

import static java.util.Objects.requireNonNull;

public final class Basket {

    private AccountId accountId;

    public void forAccount(final AccountId accountId) {
        requireNonNull(accountId, "Account Id is required");
        this.accountId = accountId;
    }

    public void addBook(final BookId bookId) {
        throw new UnsupportedOperationException();
    }

    public AccountId getAccountId() {
        return accountId;
    }
}
