package com.orderbookservice.service;

import com.orderbookservice.model.Author;
import com.orderbookservice.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderBookServiceImpl implements OrderBookService {

    private final RestTemplate restTemplate = new RestTemplate();
    private String bookServiceUrl = "http://localhost:8181/bookservice/api";

    @Override
    public Book[] getBooks() {
        return restTemplate.getForObject(bookServiceUrl, Book[].class);
    }

    @Override
    public Author getAuthor(String id) {
        String authorServiceUrl = "http://localhost:8282/authorservice/api/" + id;
        return restTemplate.getForObject(authorServiceUrl, Author.class);
    }
}
