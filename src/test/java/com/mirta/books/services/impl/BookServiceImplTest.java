package com.mirta.books.services.impl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;

import static com.mirta.books.TestData.testBook;
import static com.mirta.books.TestData.testBookEntity;

import com.mirta.books.domain.Book;
import com.mirta.books.repositories.BookRepository;
import com.mirta.books.services.impl.BookServiceImpl;
import com.mirta.books.domain.BookEntity;


@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl underTest;

    @Test
    public void testSavedBook() {
        final Book book = testBook();
        final BookEntity bookEntity = testBookEntity();

        when(bookRepository.save(eq(bookEntity))).thenReturn(bookEntity);

        final Book result = underTest.create(book);
        assertEquals(book,result);
    }

    @Test
    public void testFindByIdReturnsEmptyWhenNoBook() {
        final String isbn = "123123123";

        when(bookRepository.findById(eq(isbn))).thenReturn(Optional.empty());
        final Optional<Book> result = underTest.findById(isbn);
        assertEquals(Optional.empty(), result);
    }
    
}
