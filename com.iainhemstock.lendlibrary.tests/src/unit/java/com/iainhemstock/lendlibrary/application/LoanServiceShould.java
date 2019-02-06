package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.domain.model.accounts.AccountId;
import com.iainhemstock.lendlibrary.domain.model.catalogue.BookId;
import com.iainhemstock.lendlibrary.domain.model.transactions.Basket;
import com.iainhemstock.lendlibrary.domain.model.transactions.BookLoanRepository;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

public abstract class LoanServiceShould {

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @Mock protected BookLoanRepository bookLoanRepository;
    @Mock protected Basket basket;

    protected abstract LoanService getLoanService();

    @Test
    public void throw_when_assigning_null_account_id_to_basket() {
        try {
            getLoanService().beginCheckoutFor(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Account Id is required")));
        }
    }

    @Test
    public void assign_an_account_to_the_checkout_basket() {
        AccountId accountId = Mockito.mock(AccountId.class);
        getLoanService().beginCheckoutFor(accountId);
        verify(basket).forAccount(accountId);
    }

    @Test
    public void throw_when_adding_null_book_id_to_basket() {
        try {
            getLoanService().addToBasket(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book Id is required")));
        }
    }

    @Test
    public void add_book_to_checkout_basket() {
        BookId bookId = Mockito.mock(BookId.class);
        getLoanService().addToBasket(bookId);
        verify(basket).addBook(bookId);
    }
}
