package com.koakh.springbootgraphqlstarter.configuration;

import com.koakh.springbootgraphqlstarter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphqlConfiguration {

  @Bean
  public PostResolver postResolver(AuthorDao authorDao) {

    return new PostResolver(authorDao);
  }

  @Bean
  public AuthorResolver authorResolver(PostDao postDao) {

    return new AuthorResolver(postDao);
  }

  @Bean
  public Query query(PostDao postDao, AuthorDao authorDao) {

    return new Query(postDao, authorDao);
  }

  @Bean
  public Mutation mutation(PostDao postDao) {

    return new Mutation(postDao);
  }
}
