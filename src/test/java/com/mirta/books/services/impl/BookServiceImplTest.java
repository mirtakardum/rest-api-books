package com.mirta.books.services.impl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mirta.books.domain.Book;
import com.mirta.books.repositories.BookRepository;
import com.mirta.services.impl.BookServiceImpl;
import com.mirta.books.domain.BookEntity;


@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl underTest;

    @Test
    public void testSavedBook() {
        final Book book = Book.builder()
            .isbn("09780198709718")
            .author("Fyodor Dostoevsky")
            .title("Crime and Punishment")
            .genre("Novel")
            .build();

        final BookEntity bookEntity = BookEntity.builder()
            .isbn("09780198709718")
            .author("Fyodor Dostoevsky")
            .title("Crime and Punishment")
            .genre("Novel")
            .build();

        when(bookRepository.save(eq(bookEntity))).thenReturn(bookEntity);

        final Book result = underTest.create(book);
        assertEquals(book,result);
    }

    
}
