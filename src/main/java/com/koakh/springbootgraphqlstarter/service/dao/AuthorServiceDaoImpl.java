package com.koakh.springbootgraphqlstarter.service.dao;

import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.service.AuthorService;

import java.util.List;
import java.util.Optional;

public class AuthorServiceDaoImpl implements AuthorService {

  @Override
  public Optional<Author> getAuthor(String id) {

    return null;
  }

  @Override
  public List<Author> getAuthors(int count, int offset) {
    return null;
  }

  @Override
  public Author addAuthor(Author author) {
    return null;
  }
}
