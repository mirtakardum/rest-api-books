package com.mirta.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirta.books.domain.Book;
import com.mirta.books.domain.BookEntity;
import com.mirta.books.repositories.BookRepository;
import com.mirta.services.BookService;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(final Book book) {
        final BookEntity bookEntity = booktoBookEntity(book);
        final BookEntity savedBookEntity = bookRepository.save(bookEntity);
        return bookEntitytoBook(savedBookEntity);
    }

    private BookEntity booktoBookEntity(Book book){
       return BookEntity.builder()
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .title(book.getTitle())
                .genre(book.getGenre())
                .build();
    }

    private Book bookEntitytoBook(BookEntity bookEntity){
        return Book.builder()
                 .isbn(bookEntity.getIsbn())
                 .author(bookEntity.getAuthor())
                 .title(bookEntity.getTitle())
                 .genre(bookEntity.getGenre())
                 .build();
    }
    
}
