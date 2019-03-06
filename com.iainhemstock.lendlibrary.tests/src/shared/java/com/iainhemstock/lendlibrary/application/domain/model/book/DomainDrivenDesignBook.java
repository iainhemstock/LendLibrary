package com.iainhemstock.lendlibrary.application.domain.model.book;

import com.iainhemstock.lendlibrary.domain.model.book.*;
import com.iainhemstock.lendlibrary.domain.shared.Id;

public final class DomainDrivenDesignBook extends Book {

    public DomainDrivenDesignBook(final String bookId) {
        super(new BookId(bookId),
                new Isbn("0321125215"),
                new Title("Domain Driven Design"),
                new Subtitle("Tackling Complexity in the Heart of Software"),
                new AuthorId(4),
                new PublisherId(4),
                new Year(2004),
                new PageCount(529),
                new Description("The description of Domain Driven Design"));
    }

}
