package com.authorservice.controller;

import com.authorservice.dto.AuthorRequest;
import com.authorservice.dto.AuthorResponse;
import com.authorservice.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authorservice/api")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorRequest authorRequest) {
        try {
            AuthorResponse authorResponse = authorService.createAuthor(authorRequest);
            return new ResponseEntity<>(authorResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            log.warn("Error creating author {} :", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        try {
            List<AuthorResponse> authorResponses = authorService.getAllAuthors();
            return new ResponseEntity<>(authorResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AuthorResponse>> getAuthorById(@PathVariable("id") Long id) {
        try {
            Optional<AuthorResponse> authorResponse = authorService.getAuthorById(id);
            return new ResponseEntity<>(authorResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("id") Long id) {
        try {
            authorService.deleteAuthor(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
