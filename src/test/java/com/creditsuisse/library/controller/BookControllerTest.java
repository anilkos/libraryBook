package com.creditsuisse.library.controller;

import com.creditsuisse.library.exception.BookNotFoundException;
import com.creditsuisse.library.model.book.Book;
import com.creditsuisse.library.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void booksList() throws Exception {
        //"{\"id\":10001,\"title\":\"TITLEDEFAULT\",\"isbn\":\"ISBNDEFAULT\",\"cost\":\"111\"}"
        Mockito.when(bookService.getAllBooks()).thenReturn(Arrays.asList(new Book(10001,"TITLEDEFAULT","ISBNDEFAULT","111")));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":10001,\"title\":\"TITLEDEFAULT\",\"isbn\":\"ISBNDEFAULT\",\"cost\":\"111\"}]"))
                .andReturn();

    }

    @Test
    void singleBook() throws Exception {

        Mockito.when(bookService.getBook(anyInt())).thenReturn(java.util.Optional.of(new Book(10001, "TITLEDEFAULT", "ISBNDEFAULT", "111")));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books/1001");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().json("{\"id\":10001,\"title\":\"TITLEDEFAULT\",\"isbn\":\"ISBNDEFAULT\",\"cost\":\"111\"}"))
                .andReturn();

    }

    @Test
    void createBook() throws Exception {

        when(bookService.createBook(any())).thenReturn(new Book(10001, "TITLEDEFAULT", "ISBNDEFAULT", "111"));
        Book newBook = new Book(10001, "TITLEDEFAULT", "ISBNDEFAULT", "111");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(newBook)))
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    void deleteBookById_withException() throws Exception {
        doNothing().when(bookService).deleteBook(anyInt());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/books/1001");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isNotFound())
               .andReturn();
    }

}