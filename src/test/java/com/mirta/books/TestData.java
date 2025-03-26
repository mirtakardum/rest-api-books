package com.mirta.books;

import com.mirta.books.domain.Book;
import com.mirta.books.domain.BookEntity;

public final class TestData {
    
    private TestData() {
    }

    public static Book testBook() {
        return Book.builder()
            .isbn("09780198709718")
            .author("Fyodor Dostoevsky")
            .title("Crime and Punishment")
            .genre("Novel")
            .build();
    }

    public static BookEntity testBookEntity() {
        return BookEntity.builder()
            .isbn("09780198709718")
            .author("Fyodor Dostoevsky")
            .title("Crime and Punishment")
            .genre("Novel")
            .build();
    }
}
