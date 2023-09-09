package com.orderbookservice.service;

import com.orderbookservice.model.Author;
import com.orderbookservice.model.Book;

public interface OrderBookService {

    public Book[] getBooks();

    public Author getAuthor(String id);

}
