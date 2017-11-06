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

  //public Post writePost(String title, String text, String category, Author author) {
  public Post writePost(String title, String text, String category, String authorId) {
    Post post = new Post();
    post.setId(UUID.randomUUID().toString());
    post.setTitle(title);
    post.setText(text);
    post.setCategory(category);
    //post.setAuthorId(author.getId());
    post.setAuthorId(authorId);
    postDao.savePost(post);

    return post;
  }

  public Author createAuthor(String name, String thumbnail) {
    Author author = new Author();
    author.setId(UUID.randomUUID().toString());
    author.setName(name);
    author.setThumbnail(thumbnail);
    // Add to Moke Data
    authorDao.addAuthor(author);

    return author;
  }
}
