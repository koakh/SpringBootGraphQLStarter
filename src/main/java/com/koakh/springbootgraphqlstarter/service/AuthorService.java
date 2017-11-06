package com.koakh.springbootgraphqlstarter.service;

import com.koakh.springbootgraphqlstarter.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

  Optional<Author> getAuthor(String id);

  List<Author> getAuthors(int count, int offset);

  Author addAuthor(Author author);
}
