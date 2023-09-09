package com.authorservice.service;

import com.authorservice.dto.AuthorRequest;
import com.authorservice.dto.AuthorResponse;
import com.authorservice.model.Author;
import com.authorservice.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public AuthorResponse createAuthor(AuthorRequest authorRequest) {
        Author author = Author.builder()
                .firstName(authorRequest.getFirstName())
                .lastName(authorRequest.getLastName())
                .book(authorRequest.getBook())
                .build();

        authorRepository.save(author);
        log.info("Author with id : {} is successfully created", author.getId());
        return mapToAuthorResponse(author);
    }

    @Override
    public List<AuthorResponse> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        log.info("getAllAuthors successfully retrieved");
        return authors.stream().map(this::mapToAuthorResponse).collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorResponse> getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        log.info("getAuthorById: {} successfully retrieved", id);
        return author.map(this::mapToAuthorResponse);
    }

    @Override
    public void deleteAuthor(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        authorRepository.deleteById(id);
        log.info("Author with id: {} is successfully deleted", id);
    }

    private AuthorResponse mapToAuthorResponse(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .book(author.getBook())
                .build();
    }
}
