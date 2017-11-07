package com.koakh.springbootgraphqlstarter.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.domain.Post;
import com.koakh.springbootgraphqlstarter.service.AuthorService;
import com.koakh.springbootgraphqlstarter.service.PostService;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Root Mutation Resolver
 */
@Component
public class Mutation implements GraphQLMutationResolver {

  private final PostService postService;
  private final AuthorService authorService;

  // Must be InSync with GraphqlConfiguration.Mutation
  public Mutation(PostService postService, AuthorService authorService) {

    this.postService = postService;
    this.authorService = authorService;
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
    postService.savePost(post);

    return post;
  }

  public Author createAuthor(String name, String thumbnail) {
    Author author = new Author();
    author.setId(UUID.randomUUID().toString());
    author.setName(name);
    author.setThumbnail(thumbnail);
    // Add to Moke Data
    authorService.addAuthor(author);

    return author;
  }
}
