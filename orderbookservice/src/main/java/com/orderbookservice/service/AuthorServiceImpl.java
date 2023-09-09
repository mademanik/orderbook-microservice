package com.orderbookservice.service;

import com.orderbookservice.dto.BookDto;
import com.orderbookservice.dto.OrderBookDto;
import com.orderbookservice.model.Author;
import com.orderbookservice.model.Book;
import com.orderbookservice.model.BookAuthor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private OrderBookService orderBookService;

    @Override
    public OrderBookDto getAuthorWithBooks(String id) {
        Author author = orderBookService.getAuthor(id);
        Book[] books = orderBookService.getBooks();

        OrderBookDto orderBookDto = new OrderBookDto();
        orderBookDto.setId(author.getId());
        orderBookDto.setFirstName(author.getFirstName());
        orderBookDto.setLastName(author.getLastName());

        List<BookDto> bookDtos = new ArrayList<>();

        for (BookAuthor bookAuthor : author.getBook()) {
            for (Book book : books) {
                if (bookAuthor.getBookId().equals(book.getId())) {
                    BookDto bookDto = new BookDto();
                    bookDto.setId(book.getId());
                    bookDto.setTitle(book.getTitle());
                    bookDto.setDescription(book.getDescription());
                    bookDtos.add(bookDto);
                }
            }
        }

        orderBookDto.setBook(bookDtos);
        return orderBookDto;
    }
}
