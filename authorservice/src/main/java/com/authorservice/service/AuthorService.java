package com.authorservice.service;

import com.authorservice.dto.AuthorRequest;
import com.authorservice.dto.AuthorResponse;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    public AuthorResponse createAuthor(AuthorRequest authorRequest);

    public List<AuthorResponse> getAllAuthors();

    public Optional<AuthorResponse> getAuthorById(Long id);

    public void deleteAuthor(Long id);
}
