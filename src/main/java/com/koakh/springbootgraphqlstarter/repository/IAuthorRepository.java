package com.koakh.springbootgraphqlstarter.repository;

import com.koakh.springbootgraphqlstarter.domain.Author;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IAuthorRepository {

  Optional<Author> getAuthor(String id);

  List<Author> getAuthors(int count, int offset);

  Author addAuthor(Author author);
}
