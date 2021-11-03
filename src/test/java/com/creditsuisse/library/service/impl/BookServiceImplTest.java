package com.creditsuisse.library.service.impl;

import com.creditsuisse.library.model.book.Book;
import com.creditsuisse.library.repository.book.BookRepository;
import com.creditsuisse.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void createBook() {
        Mockito.when(bookRepository.save(any())).thenReturn(new Book(10001,"TITLE","ISBN","111"));
        Book expected = new Book(10001,"TITLE","ISBN","111");
        assertEquals(expected.getId(),bookRepository.save(new Book(10001,"TITLE","ISBN","111")).getId());
        assertEquals(expected.getIsbn(),bookRepository.save(new Book(10001,"TITLE","ISBN","111")).getIsbn());
    }


    @Test
    void findByTitleIgnoreCaseContaining() {
        Mockito.when(bookRepository.findByTitleIgnoreCaseContaining(anyString())).thenReturn(Arrays.asList(new Book(10001,"TITLE","ISBN","111")));
        assertEquals(1,bookRepository.findByTitleIgnoreCaseContaining("title").size());
    }

    @Test
    void findByIsbnIgnoreCaseContaining() {
        Mockito.when(bookRepository.findByIsbnIgnoreCaseContaining(anyString())).thenReturn(Arrays.asList(new Book(10001,"TITLE","ISBN","111")));
        assertEquals(1,bookRepository.findByIsbnIgnoreCaseContaining("isbn").size());

    }

    @Test
    void count() {
        Mockito.when(bookRepository.count()).thenReturn(1L);
        assertEquals(1L,bookRepository.count());

    }
}