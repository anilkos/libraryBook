package com.creditsuisse.library.service;

import com.creditsuisse.library.model.book.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book createBook(Book book);

    void deleteBook(int id);

    Book updateBook(Book book);

    List<Book> getAllBooks();

    Optional<Book> getBook(int id);

    List<Book> findByTitleIgnoreCaseContaining(String searchTerm);

    List<Book> findByIsbnIgnoreCaseContaining(String searchTerm);

    long count();

}
