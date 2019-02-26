package com.iainhemstock.lendlibrary.domain.model.book;

import com.iainhemstock.lendlibrary.domain.shared.Repository;

public abstract class BookRepository extends Repository<Book> {

    public BookId nextId() {
        return new BookId(super.generateUniqueId());
    }

    public abstract Book getById(final BookId bookId);
}
