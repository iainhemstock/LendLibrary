package com.iainhemstock.lendlibrary.domain.transactions;

import com.iainhemstock.lendlibrary.domain.accounts.valueobjects.AccountId;
import com.iainhemstock.lendlibrary.domain.books.valueobjects.BookId;
import com.iainhemstock.lendlibrary.domain.transactions.entities.BookLoan;
import com.iainhemstock.lendlibrary.domain.transactions.valueobjects.Basket;
import com.iainhemstock.lendlibrary.domain.transactions.valueobjects.LoanId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class BasketShould {

    private Basket basket;
    @Mock private LoanId loanId;
    @Mock private AccountId accountId;
    @Mock private BookId bookId;

    @Before
    public void setUp() {
        loanId = Mockito.mock(LoanId.class);
        accountId = Mockito.mock(AccountId.class);
        bookId = Mockito.mock(BookId.class);
        basket = new Basket();
    }

    @Test
    public void throw_when_trying_to_register_null_account_id_with_basket() {
        try {
            basket.forAccount(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Account Id is required")));
        }
    }

    @Test
    public void register_account_with_basket() {
        basket.forAccount(accountId);
        assertThat(basket.getAccountId(), is(equalTo(accountId)));
    }

    @Test
    public void add_book_to_its_contents() {
        basket.forAccount(accountId);
        basket.addBook(bookId);
        List<BookLoan> basketContents = basket.getContents();
        assertThat(basketContents, is(equalTo(expectedBaskContents())));
    }

    private List<BookLoan> expectedBaskContents() {
        return Arrays.asList(new BookLoan(loanId, accountId, bookId, rentalPeriod));
    }
}
