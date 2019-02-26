package com.iainhemstock.lendlibrary.application.cataloging;

import com.iainhemstock.lendlibrary.application.cataloging.dto.BookDTO;
import com.iainhemstock.lendlibrary.domain.model.book.Book;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;

import java.util.List;

public interface CatalogingServiceAdapter {

    String addBook(final BookDTO bookDTO);

    List<BookDTO> getAllBooks();

    BookDTO getBook(String bookId);
}
