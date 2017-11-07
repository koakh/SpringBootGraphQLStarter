package com.koakh.springbootgraphqlstarter.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.domain.Post;
import com.koakh.springbootgraphqlstarter.service.PostService;

import java.util.List;

public class AuthorResolver implements GraphQLResolver<Author> {

  private PostService postService;

  public AuthorResolver(PostService postService) {

    this.postService = postService;
  }

  public List<Post> getPosts(Author author) {

    return postService.getAuthorPosts(author.getId());
  }
}
