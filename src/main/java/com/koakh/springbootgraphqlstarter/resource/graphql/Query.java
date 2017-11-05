package com.koakh.springbootgraphqlstarter.resource.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.koakh.springbootgraphqlstarter.dao.AuthorDao;
import com.koakh.springbootgraphqlstarter.dao.PostDao;
import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.domain.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Root Query Resolver
 * The root query needs to have special beans defined in the Spring context to handle the various fields in this root query. Unlike the schema definition, there is no restriction that there only be a single Spring bean for the root query fields.
 * The only requirements are that the beans implement GraphQLQueryResolver and that every field in the root query from the schema has a method in one of these classes with the same name.
 */
@Component
public class Query implements GraphQLQueryResolver {
  private PostDao postDao;
  private AuthorDao authorDao;

  // Must be InSync with GraphqlConfiguration.Query
  public Query(PostDao postDao, AuthorDao authorDao) {

    this.postDao = postDao;
    this.authorDao = authorDao;
  }

  public String version() {

    return "1.0.0";
  }

  public List<Post> recentPosts(int count, int offset) {

    return postDao.getRecentPosts(count, offset);
  }

  public Optional<Author> getAuthor(String id) {

    return authorDao.getAuthor(id);
  }
}
