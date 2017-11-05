package com.koakh.springbootgraphqlstarter.resource.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.koakh.springbootgraphqlstarter.dao.AuthorDao;
import com.koakh.springbootgraphqlstarter.dao.PostDao;
import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.domain.Post;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Root Mutation Resolver
 */
@Component
public class Mutation implements GraphQLMutationResolver {
  private PostDao postDao;
  private AuthorDao authorDao;

  // Must be InSync with GraphqlConfiguration.Mutation
  public Mutation(PostDao postDao, AuthorDao authorDao) {

    this.postDao = postDao;
    this.authorDao = authorDao;
  }

  public Post writePost(String title, String text, String category, Author author) {
    Post post = new Post();
    post.setId(UUID.randomUUID().toString());
    post.setTitle(title);
    post.setText(text);
    post.setCategory(category);
    post.setAuthorId(author.getId());
    postDao.savePost(post);

    return post;
  }

  public Author createAuthor(String name) {
    //public Author createAuthor(Author author) {
    //authorDao.addAuthor(author);
    Author author = new Author();
    author.setId(name.toLowerCase());
    author.setName(name);

    return author;
  }
}
