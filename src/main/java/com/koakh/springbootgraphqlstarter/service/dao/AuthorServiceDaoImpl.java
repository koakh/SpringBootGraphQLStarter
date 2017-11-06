package com.koakh.springbootgraphqlstarter.service.dao;

import com.koakh.springbootgraphqlstarter.dao.AuthorDao;
import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.service.AuthorService;

import java.util.List;
import java.util.Optional;

public class AuthorServiceDaoImpl implements AuthorService {

  private AuthorDao authorDao;

  public AuthorServiceDaoImpl(AuthorDao authorDao) {
    this.authorDao = authorDao;
  }

  @Override
  public Optional<Author> getAuthor(String id) {

    return this.authorDao.getAuthor(id);
  }

  @Override
  public List<Author> getAuthors(int count, int offset) {

    return this.authorDao.getAuthors(count, offset);
  }

  @Override
  public Author addAuthor(Author author) {

    return this.authorDao.addAuthor(author);
  }
}
