package com.iainhemstock.lendlibrary.application.domain.model.book;

import com.iainhemstock.lendlibrary.domain.model.book.*;

public final class JavaCompleteReferenceBook extends Book {

    public JavaCompleteReferenceBook(final String bookId) {
        super(new BookId(bookId),
                new Isbn("1259589331"),
                new Title("Java"),
                new Subtitle("The Complete Reference"),
                new AuthorId(9),
                new PublisherId(9),
                new Year(2017),
                new PageCount(1344),
                new Description("The description of Java Complete Reference"));
    }

}
