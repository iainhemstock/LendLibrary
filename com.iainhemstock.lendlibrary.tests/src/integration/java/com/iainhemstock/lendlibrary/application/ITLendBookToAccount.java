package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.application.LoanService;
import com.iainhemstock.lendlibrary.application.impls.SimpleLoanService;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountId;
import com.iainhemstock.lendlibrary.domain.model.catalogue.BookId;
import com.iainhemstock.lendlibrary.domain.model.transactions.BookLoan;
import com.iainhemstock.lendlibrary.domain.model.transactions.Basket;
import com.iainhemstock.lendlibrary.domain.model.transactions.LoanId;
import com.iainhemstock.lendlibrary.domain.model.transactions.RentalPeriod;
import com.iainhemstock.lendlibrary.infrastructure.persistence.AccountRepositoryMemory;
import com.iainhemstock.lendlibrary.infrastructure.persistence.CatalogueRepositoryMemory;
import com.iainhemstock.lendlibrary.infrastructure.persistence.BookLoanRepositoryMemory;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountRepository;
import com.iainhemstock.lendlibrary.domain.model.catalogue.CatalogueRepository;
import com.iainhemstock.lendlibrary.domain.model.transactions.BookLoanRepository;
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

public final class ITLendBookToAccount {

    private static final int APRIL = 3;

    private LoanService loanService;
    private BookLoanRepository bookLoanRepository;
    private AccountId accountId;
    private BookId bookId;
    private AccountRepository accountRepository;
    private CatalogueRepository catalogueRepository;

    @Before
    public void setUp() {
        bookLoanRepository = new TestableBookLoanRepository();
        Basket basket = new Basket();
        loanService = new SimpleLoanService(bookLoanRepository, basket);
        accountRepository = new AccountRepositoryMemory();
        catalogueRepository = new CatalogueRepositoryMemory();
    }

    @Test
    @Ignore
    public void lend_book_to_account() {

        this.accountId = accountRepository.nextId();
        this.bookId = catalogueRepository.nextId();

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

    private class TestableBookLoanRepository extends BookLoanRepositoryMemory {
        private LoanId loanId = new LoanId(UUID.randomUUID().toString());
        @Override
        public LoanId nextId() {
            return loanId;
        }
    }
}
