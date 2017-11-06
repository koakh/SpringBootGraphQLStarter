package com.koakh.springbootgraphqlstarter.service.h2;

import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.repository.AuthorRepository;
import com.koakh.springbootgraphqlstarter.service.AuthorService;

import java.util.List;
import java.util.Optional;

public class AuthorServiceH2Impl implements AuthorService {

  private AuthorRepository repository;

  public AuthorServiceH2Impl(AuthorRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<Author> getAuthor(String id) {
    return Optional.ofNullable(repository.findOne(id));
  }

  @Override
  public List<Author> getAuthors(int count, int offset) {
    return (List<Author>) repository.findAll();
  }

  @Override
  public Author addAuthor(Author author) {
    return repository.save(author);
  }
}
