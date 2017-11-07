package com.koakh.springbootgraphqlstarter.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.domain.Post;
import com.koakh.springbootgraphqlstarter.service.AuthorService;

import java.util.Optional;

public class PostResolver implements GraphQLResolver<Post> {

  private AuthorService authorService;

  public PostResolver(AuthorService authorService) {

    this.authorService = authorService;
  }

  public Optional<Author> getAuthor(Post post) {

    return authorService.getAuthor(post.getAuthorId());
  }
}
