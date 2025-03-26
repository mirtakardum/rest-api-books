package com.mirta.books.services;
import java.util.Optional;

import com.mirta.books.domain.Book;

public interface BookService {
    
    Book create (Book book);

    Optional<Book> findById(String isbn);
}
