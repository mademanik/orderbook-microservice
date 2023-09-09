package com.orderbookservice.controller;

import com.orderbookservice.dto.OrderBookDto;
import com.orderbookservice.model.Author;
import com.orderbookservice.model.Book;
import com.orderbookservice.service.AuthorService;
import com.orderbookservice.service.OrderBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderbookservice/api")
@Slf4j
public class OrderBookController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderBookDto> getAuthorWithBooks(@PathVariable("id") String id) {

        try {
            OrderBookDto orderBookDto = authorService.getAuthorWithBooks(id);
            return new ResponseEntity<>(orderBookDto, HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
