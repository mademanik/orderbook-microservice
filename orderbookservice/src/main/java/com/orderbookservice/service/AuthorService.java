package com.orderbookservice.service;

import com.orderbookservice.dto.OrderBookDto;

public interface AuthorService {

    public OrderBookDto getAuthorWithBooks(String id);

}
