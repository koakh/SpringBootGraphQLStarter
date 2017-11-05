package com.koakh.springbootgraphqlstarter.resource.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.koakh.springbootgraphqlstarter.dao.AuthorDao;
import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.domain.Post;

import java.util.Optional;

public class PostResolver implements GraphQLResolver<Post> {
  private AuthorDao authorDao;

  public PostResolver(AuthorDao authorDao) {

    this.authorDao = authorDao;
  }

  public Optional<Author> getAuthor(Post post) {

    return authorDao.getAuthor(post.getAuthorId());
  }
}
