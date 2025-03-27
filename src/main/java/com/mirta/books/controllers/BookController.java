package com.mirta.books.controllers;
import com.mirta.books.domain.Book;
import com.mirta.books.services.BookService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    
    private final BookService bookService;

    @Autowired
    public BookController(final BookService bookService){
        this.bookService = bookService;
    }

    @PutMapping(path= "/books/{isbn}")
    public ResponseEntity<Book> createBook(
        @PathVariable final String isbn, 
        @RequestBody final Book book) {
            book.setIsbn(isbn);
            final Book savedBook = bookService.create(book);
            final ResponseEntity<Book> response = new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
            return response;
        }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<Book> retrieveBook(@PathVariable final String isbn){
        final Optional<Book> foundBook = bookService.findById(isbn);
        return foundBook.map(book -> new ResponseEntity<Book>(book, HttpStatus.OK))
        .orElse(new ResponseEntity<Book>(HttpStatus.NOT_FOUND));
    }
}
