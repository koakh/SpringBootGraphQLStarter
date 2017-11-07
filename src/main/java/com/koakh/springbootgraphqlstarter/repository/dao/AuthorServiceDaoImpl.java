package com.koakh.springbootgraphqlstarter.repository.dao;

import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Qualifier("daoRepository")
public class AuthorServiceDaoImpl implements IAuthorRepository {

  private final List<Author> authors;

  public AuthorServiceDaoImpl(List<Author> authors) {

    this.authors = authors;
  }

  @Override
  public Optional<Author> getAuthor(String id) {

    return authors.stream()
      .filter(author -> id.equals(author.getId()))
      .findFirst();
  }

  @Override
  public List<Author> getAuthors(int count, int offset) {
    return authors.stream()
      .skip(offset)
      .limit(count)
      .collect(Collectors.toList());
  }

  @Override
  public Author addAuthor(Author author) {
    authors.add(0, author);

    return author;
  }
}
