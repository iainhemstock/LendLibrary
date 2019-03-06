package com.iainhemstock.lendlibrary.application.cataloging.impls;

import com.iainhemstock.lendlibrary.application.cataloging.CatalogingService;
import com.iainhemstock.lendlibrary.application.cataloging.dto.BookDTO;
import com.iainhemstock.lendlibrary.application.cataloging.impls.assembler.BookDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.book.Book;
import com.iainhemstock.lendlibrary.domain.model.book.BookFactory;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.book.BookRepository;
import com.iainhemstock.lendlibrary.domain.shared.Id;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class CatalogingServiceImpl implements CatalogingService {

    private BookRepository bookRepository;
    private BookFactory bookFactory;
    private BookDTOAssembler bookDTOAssembler;

    public CatalogingServiceImpl(final BookRepository bookRepository, BookFactory bookFactory,
                                 BookDTOAssembler bookDTOAssembler) {
        this.bookRepository = bookRepository;
        this.bookFactory = bookFactory;
        this.bookDTOAssembler = bookDTOAssembler;
        requireNonNull(this.bookRepository, "Book repository is required");
        requireNonNull(this.bookFactory, "Book factory is required");
        requireNonNull(this.bookDTOAssembler, "Book DTO assembler is required");
    }

    @Override
    public String addBookToCatalog(final BookDTO bookDTO) {
        requireNonNull(bookDTO, "Book DTO is required");

        String bookId = bookRepository.nextId().toString();
        Book book = bookFactory.create(
                bookId,
                bookDTO.getIsbn(),
                bookDTO.getTitle(),
                bookDTO.getSubtitle(),
                bookDTO.getAuthorId(),
                bookDTO.getPublisherId(),
                bookDTO.getYearPublished(),
                bookDTO.getPageCount(),
                bookDTO.getDescription());

        bookRepository.add(book);
        return bookId;
    }

    @Override
    public List<BookDTO> fetchAllBooks() {
        List<Book> allBooks = bookRepository.getAll();
        return bookDTOAssembler.toDTOList(allBooks);
    }

    @Override
    public BookDTO fetchBook(final String bookId) {
        requireNonNull(bookId, "Book id is required");
        Book book = bookRepository.getById(new BookId(bookId));
        return bookDTOAssembler.toDTO(book);
    }
}
