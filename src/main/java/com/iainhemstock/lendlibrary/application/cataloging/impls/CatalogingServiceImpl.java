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

    public CatalogingServiceImpl(final BookRepository bookRepository, BookFactory bookFactory) {
        this.bookRepository = bookRepository;
        this.bookFactory = bookFactory;
        requireNonNull(this.bookRepository, "Book repository is required");
        requireNonNull(this.bookFactory, "Book factory is required");
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
                bookDTO.getAuthor(),
                bookDTO.getPublisher(),
                bookDTO.getYearPublished(),
                bookDTO.getPageCount(),
                bookDTO.getDescription());

        bookRepository.add(book);
        return bookId;
    }

    @Override
    public List<BookDTO> fetchAllBooks() {
        List<Book> allBooks = bookRepository.getAll();
        BookDTOAssembler assembler = new BookDTOAssembler();
        return assembler.toDTOList(allBooks);
    }

    @Override
    public BookDTO fetchBook(final String bookId) {
        requireNonNull(bookId, "Book id is required");
        Book book = bookRepository.getById(new BookId(bookId));
        BookDTOAssembler assembler = new BookDTOAssembler();
        return assembler.toDTO(book);
    }
}
