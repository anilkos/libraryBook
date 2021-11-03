package com.creditsuisse.library.service.impl;

import com.creditsuisse.library.service.BookService;
import com.creditsuisse.library.model.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.creditsuisse.library.repository.book.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBook(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findByTitleIgnoreCaseContaining(String searchTerm) {
        return bookRepository.findByTitleIgnoreCaseContaining(searchTerm);
    }

    @Override
    public List<Book> findByIsbnIgnoreCaseContaining(String searchTerm) {
        return bookRepository.findByIsbnIgnoreCaseContaining(searchTerm);
    }

    @Override
    public long count() {
        return bookRepository.count();
    }
}
