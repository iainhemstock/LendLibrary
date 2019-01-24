package com.iainhemstock.lendlibrary.application.transactions;

import com.iainhemstock.lendlibrary.application.SimpleLoanService;
import com.iainhemstock.lendlibrary.domain.accounts.valueobjects.AccountId;
import com.iainhemstock.lendlibrary.domain.books.valueobjects.BookId;
import com.iainhemstock.lendlibrary.domain.transactions.entities.BookLoan;
import com.iainhemstock.lendlibrary.domain.transactions.valueobjects.Basket;
import com.iainhemstock.lendlibrary.domain.transactions.valueobjects.LoanId;
import com.iainhemstock.lendlibrary.domain.transactions.valueobjects.RentalPeriod;
import com.iainhemstock.lendlibrary.infrastructure.persistence.AccountInMemoryRepository;
import com.iainhemstock.lendlibrary.infrastructure.persistence.BookInMemoryRepository;
import com.iainhemstock.lendlibrary.infrastructure.persistence.BookLoanInMemoryRepository;
import com.iainhemstock.lendlibrary.interfaces.applicationservices.LoanService;
import com.iainhemstock.lendlibrary.interfaces.repository.AccountRepository;
import com.iainhemstock.lendlibrary.interfaces.repository.BookRepository;
import com.iainhemstock.lendlibrary.interfaces.repository.BookLoanRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class LendBookToAccount {

    private static final int APRIL = 3;

    private LoanService loanService;
    private BookLoanRepository bookLoanRepository;
    private AccountId accountId;
    private BookId bookId;
    private AccountRepository accountRepository;
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        bookLoanRepository = new TestableBookLoanRepository();
        Basket basket = new Basket();
        loanService = new SimpleLoanService(bookLoanRepository, basket);
        accountRepository = new AccountInMemoryRepository();
        bookRepository = new BookInMemoryRepository();
    }

    @Test
    public void lend_book_to_account() {

        this.accountId = accountRepository.nextId();
        this.bookId = bookRepository.nextId();

        loanService.beginCheckoutFor(accountId);
        loanService.addToBasket(bookId);
        loanService.confirmCheckout();

        assertThat(bookLoanRepository.size(), is(equalTo(1)));
        assertTrue(bookLoanRepository.contains(expectedBookLoan()));
    }

    private BookLoan expectedBookLoan() {
        LoanId loanId = bookLoanRepository.nextId();
        Date startDate = makeDate(2019, APRIL, 15);
        Date endDate = makeDate(2019, APRIL, 29);
        RentalPeriod rentalPeriod = new RentalPeriod(startDate, endDate);
        return new BookLoan(loanId, accountId, bookId, rentalPeriod);
    }

    private Date makeDate(final int year, final int month, final int date) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, month, date);
        return calendar.getTime();
    }

    private class TestableBookLoanRepository extends BookLoanInMemoryRepository {
        private LoanId loanId = new LoanId(UUID.randomUUID().toString());
        @Override
        public LoanId nextId() {
            return loanId;
        }
    }
}
