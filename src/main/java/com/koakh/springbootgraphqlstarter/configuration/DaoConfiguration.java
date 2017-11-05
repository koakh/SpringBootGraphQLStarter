package com.koakh.springbootgraphqlstarter.configuration;

import com.koakh.springbootgraphqlstarter.Author;
import com.koakh.springbootgraphqlstarter.AuthorDao;
import com.koakh.springbootgraphqlstarter.Post;
import com.koakh.springbootgraphqlstarter.PostDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DaoConfiguration {
  @Bean
  public PostDao postDao() {
    List<Post> posts = new ArrayList<>();
    // Moke some Posts
    for (int postId = 0; postId < 10; ++postId) {
      for (int authorId = 0; authorId < 10; ++authorId) {
        Post post = new Post();
        post.setId("Post" + authorId + postId);
        post.setTitle("Post " + authorId + ":" + postId);
        post.setText("Post " + postId + " + by author " + authorId);
        post.setAuthorId("Author" + authorId);
        posts.add(post);
      }
    }
    return new PostDao(posts);
  }

  @Bean
  public AuthorDao authorDao() {
    List<Author> authors = new ArrayList<>();
    // Moke some Authors
    for (int authorId = 0; authorId < 10; ++authorId) {
      Author author = new Author();
      author.setId("Author" + authorId);
      author.setName("Author " + authorId);
      author.setThumbnail("http://example.com/authors/" + authorId);
      authors.add(author);
    }
    return new AuthorDao(authors);
  }
}
