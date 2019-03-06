package com.iainhemstock.lendlibrary.application.domain.model.book;

import com.iainhemstock.lendlibrary.domain.model.book.*;
import com.iainhemstock.lendlibrary.domain.shared.Id;

public final class HeadFirstDesignPatternsBook extends Book {

    public HeadFirstDesignPatternsBook(final String bookId) {
        super(new BookId(bookId),
                new Isbn("9780596007126"),
                new Title("Head First Design Patterns"),
                new Subtitle("Your brain on design patterns"),
                new AuthorId(12),
                new PublisherId(12),
                new Year(2004),
                new PageCount(638),
                new Description("The description of Head First Design Patterns"));
    }


}
