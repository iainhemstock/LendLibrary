package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.application.domain.model.book.DomainDrivenDesignBook;
import com.iainhemstock.lendlibrary.application.domain.model.book.HeadFirstDesignPatternsBook;
import com.iainhemstock.lendlibrary.application.domain.model.book.JavaCompleteReferenceBook;
import com.iainhemstock.lendlibrary.domain.model.book.*;
import com.iainhemstock.lendlibrary.domain.shared.Id;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class BookRepositoryShould extends RepositoryShould {

    protected abstract BookRepository getRepository();

    @Test
    public void throw_when_fetching_book_that_does_not_exist() {
        BookId nonExistentId = new BookId("id-1234");
        try {
            getRepository().getById(nonExistentId);
            fail("expected method under test to throw BookNotFoundException but it didn't");
        } catch (BookNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book with id <id-1234> could not be found")));
        }
    }

    @Test
    public void throw_when_updating_immutable_book() {
        HeadFirstDesignPatternsBook book = new HeadFirstDesignPatternsBook("id-1234");
        try {
            getRepository().update(book);
            fail("expected method under test to throw UpdateBookException but it didn't");
        } catch (UpdateBookException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book is immutable and cannot be updated")));
        }
    }

    @Test
    public void retrieve_book_by_id() {
        Book expectedBook = new HeadFirstDesignPatternsBook("id-1234");
        getRepository().add(expectedBook);

        Book actualItem = getRepository().getById(expectedBook.getId());

        assertThat(actualItem, is(equalTo(expectedBook)));
    }

    @Override
    public void return_false_when_repo_does_not_contain_item() {
        assertThat(getRepository().contains(new HeadFirstDesignPatternsBook("id-1234")),
                is(equalTo(false)));
    }

    @Override
    public void return_true_when_repo_contains_item() {
        Book repositoryItem = new HeadFirstDesignPatternsBook("id-1234");
        getRepository().add(repositoryItem);

        assertThat(getRepository().contains(repositoryItem),
                is(equalTo(true)));
    }

    @Override
    public void retrieve_all_items_in_repo() {
        Book item1 = new HeadFirstDesignPatternsBook("id-1234");
        Book item2 = new DomainDrivenDesignBook("id-2345");
        Book item3 = new JavaCompleteReferenceBook("id-3456");

        getRepository().add(item1);
        getRepository().add(item2);
        getRepository().add(item3);

        assertThat(getRepository().getAll(),
                is(equalTo(List.of(item1, item2, item3))));
    }

    @Override
    public void remove_item_from_repo() {
        Book item1 = new HeadFirstDesignPatternsBook("id-1234");
        Book item2 = new DomainDrivenDesignBook("id-2345");
        Book item3 = new JavaCompleteReferenceBook("id-3456");

        getRepository().add(item1);
        getRepository().add(item2);
        getRepository().add(item3);

        getRepository().remove(item2);

        assertTrue(getRepository().contains(item1));
        assertFalse("repo should not contain item after removal", getRepository().contains(item2));
        assertTrue(getRepository().contains(item3));
    }
}
