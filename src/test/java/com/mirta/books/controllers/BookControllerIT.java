package com.mirta.books.controllers;

import static com.mirta.books.TestData.testBook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirta.books.TestData;
import com.mirta.books.domain.Book;
import com.mirta.books.services.BookService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Test
    public void createdBookTest() throws Exception{
        final Book book = TestData.testBook();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String bookJson = objectMapper.writeValueAsString(book);


        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + book.getIsbn())
        .contentType(MediaType.APPLICATION_JSON)
        .content(bookJson))
        .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(book.getGenre()));
    }

    @Test
    public void testRetrievedBookReturns404WhenNotFound() throws Exception{

      mockMvc.perform(MockMvcRequestBuilders.get("/books/123123123"))
      .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testRetrievedBookReturns200WhenExists() throws Exception{
      final Book book = TestData.testBook();
      bookService.create(book);
      mockMvc.perform(MockMvcRequestBuilders.get("/books/" + book.getIsbn()))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn()))
      .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle()))
      .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor()))
      .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(book.getGenre()));
    }
}
