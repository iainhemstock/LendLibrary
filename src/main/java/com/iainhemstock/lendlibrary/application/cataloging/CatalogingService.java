package com.iainhemstock.lendlibrary.application.cataloging;

import com.iainhemstock.lendlibrary.application.cataloging.dto.BookDTO;

import java.util.List;

public interface CatalogingService {

    String addBookToCatalog(final BookDTO bookDTO);

    BookDTO fetchBook(String bookId);

    List<BookDTO> fetchAllBooks();
}
