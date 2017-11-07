package com.koakh.springbootgraphqlstarter.repository.h2;

import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("h2Repository")
public class AuthorRepositoryH2Implementation implements IAuthorRepository {

  private final AuthorRepositoryH2 repository;

  public AuthorRepositoryH2Implementation(AuthorRepositoryH2 repository) {

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
