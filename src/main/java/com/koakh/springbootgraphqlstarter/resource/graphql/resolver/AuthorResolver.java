package com.koakh.springbootgraphqlstarter.resource.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.koakh.springbootgraphqlstarter.dao.PostDao;
import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.domain.Post;

import java.util.List;

public class AuthorResolver implements GraphQLResolver<Author> {
  private PostDao postDao;

  public AuthorResolver(PostDao postDao) {

    this.postDao = postDao;
  }

  public List<Post> getPosts(Author author) {

    return postDao.getAuthorPosts(author.getId());
  }
}
