package com.koakh.springbootgraphqlstarter.configuration;

import com.koakh.springbootgraphqlstarter.dao.AuthorDao;
import com.koakh.springbootgraphqlstarter.dao.PostDao;
import com.koakh.springbootgraphqlstarter.resource.graphql.AuthorResolver;
import com.koakh.springbootgraphqlstarter.resource.graphql.Mutation;
import com.koakh.springbootgraphqlstarter.resource.graphql.PostResolver;
import com.koakh.springbootgraphqlstarter.resource.graphql.Query;
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
  public Mutation mutation(PostDao postDao, AuthorDao authorDao) {

    return new Mutation(postDao, authorDao);
  }
}
