package com.creditsuisse.library.controller;

import com.creditsuisse.library.exception.BookNotFoundException;
import com.creditsuisse.library.service.BookService;
import lombok.RequiredArgsConstructor;
import com.creditsuisse.library.model.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> booksList() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> singleBook(@PathVariable("id") int id) {
        if(!bookService.getBook(id).isPresent()){
            throw new BookNotFoundException("Book not found with id: "+id);
        }else
        return ResponseEntity.ok(bookService.getBook(id).get());
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {

            Book bookResponse = bookService.createBook(book);
            return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);

    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
        Optional<Book> bookResponse = bookService.getBook(id);

        if (bookResponse.isPresent()) {
            Book bookReturn = bookResponse.get();

            bookReturn.setIsbn(book.getIsbn());
            bookReturn.setTitle(book.getTitle());
            bookReturn.setCost(book.getCost());
            return new ResponseEntity<>(bookService.updateBook(bookReturn), HttpStatus.OK);
        } else {
            throw new BookNotFoundException("book not found with id: "+id);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("id") int id) {
        if(!bookService.getBook(id).isPresent()){
            throw new BookNotFoundException("Book not found with id: "+id);
        }else {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/books/search/{findBy}")
    public ResponseEntity<List<Book>> searchBooks(@PathVariable("findBy") String findBy, @RequestParam("searchTerm") String searchTerm) {
        if (findBy.equalsIgnoreCase("isbn")) {
            return ResponseEntity.ok(bookService.findByIsbnIgnoreCaseContaining(searchTerm));
        } else {
            return ResponseEntity.ok(bookService.findByTitleIgnoreCaseContaining(searchTerm));
        }
    }

    @GetMapping("/books/totalcount")
    public ResponseEntity<Long> bookCount() {
        return ResponseEntity.ok(bookService.count());
    }
}
