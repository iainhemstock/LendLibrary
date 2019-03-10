package com.iainhemstock.lendlibrary.application.cataloging.impls.assembler;

import com.iainhemstock.lendlibrary.application.cataloging.dto.BookDTO;
import com.iainhemstock.lendlibrary.domain.model.book.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDTOAssembler {
    public List<BookDTO> toDTOList(List<Book> books) {
        List<BookDTO> bookDTOs = new ArrayList<>();

        for (Book book : books) {
            BookDTO bookDTO = new BookDTO(
                    book.getId().toString(),
                    book.getIsbn().toString(),
                    book.getTitle().toString(),
                    book.getSubtitle().toString(),
                    book.getAuthor().toString(),
                    book.getPublisher().toString(),
                    Integer.parseInt(book.getYearPublished().toString()),
                    Integer.parseInt(book.getPageCount().toString()),
                    book.getDescription().toString());
            bookDTOs.add(bookDTO);
        }
        return bookDTOs;
    }

    public BookDTO toDTO(final Book book) {
        return toDTOList(List.of(book)).get(0);
    }
}
