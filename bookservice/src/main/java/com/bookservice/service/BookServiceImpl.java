package com.bookservice.service;

import com.bookservice.dto.BookRequest;
import com.bookservice.dto.BookResponse;
import com.bookservice.model.Book;
import com.bookservice.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookResponse createBook(BookRequest bookRequest) {
        Book book = Book.builder()
                .title(bookRequest.getTitle())
                .description(bookRequest.getDescription())
                .build();
        bookRepository.save(book);
        log.info("Book with id : {} is successfully created", book.getId());
        return mapToBookResponse(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        log.info("getAllBooks successfully retrieved");
        return books.stream().map(this::mapToBookResponse).collect(Collectors.toList());
    }

    @Override
    public Optional<BookResponse> getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        log.info("getBookById: {} successfully retrieved", id);
        return book.map(this::mapToBookResponse);
    }

    @Override
    public void deleteBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        bookRepository.deleteById(id);
        log.info("Book with id: {} is successfully deleted", id);
    }

    private BookResponse mapToBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .build();
    }
}
