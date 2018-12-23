package com.koakh.springbootgraphqlstarter.service;

import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

  private final IAuthorRepository repository;

  public AuthorService(@Qualifier("h2Repository") IAuthorRepository repository) {
    this.repository = repository;
  }

  public List<Author> getAuthors(int count, int offset) {
    return repository.getAuthors(count, offset);
  }

  public Optional<Author> getAuthor(String id) {
    return repository.getAuthor(id);
  }

  public void addAuthor(Author author) {
    repository.addAuthor(author);
  }
}
