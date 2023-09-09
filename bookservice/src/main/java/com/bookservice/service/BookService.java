package com.bookservice.service;

import com.bookservice.dto.BookRequest;
import com.bookservice.dto.BookResponse;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public BookResponse createBook(BookRequest bookRequest);

    public List<BookResponse> getAllBooks();

    public Optional<BookResponse> getBookById(Long id);

    public void deleteBookById (Long id);
}
