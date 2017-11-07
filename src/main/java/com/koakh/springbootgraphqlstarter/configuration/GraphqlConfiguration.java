package com.koakh.springbootgraphqlstarter.configuration;

import com.koakh.springbootgraphqlstarter.graphql.Mutation;
import com.koakh.springbootgraphqlstarter.graphql.Query;
import com.koakh.springbootgraphqlstarter.graphql.resolver.AuthorResolver;
import com.koakh.springbootgraphqlstarter.graphql.resolver.PostResolver;
import com.koakh.springbootgraphqlstarter.service.AuthorService;
import com.koakh.springbootgraphqlstarter.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphqlConfiguration {

  @Bean
  public PostResolver postResolver(AuthorService authorService) {

    return new PostResolver(authorService);
  }

  @Bean
  public AuthorResolver authorResolver(PostService postService) {

    return new AuthorResolver(postService);
  }

  @Bean
  public Query query(PostService postService, AuthorService authorService) {

    return new Query(postService, authorService);
  }

  @Bean
  public Mutation mutation(PostService postService, AuthorService authorService) {

    return new Mutation(postService, authorService);
  }
}
