package com.koakh.springbootgraphqlstarter.resource.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.koakh.springbootgraphqlstarter.dao.PostDao;
import com.koakh.springbootgraphqlstarter.domain.Author;
import com.koakh.springbootgraphqlstarter.domain.Post;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Mutation implements GraphQLMutationResolver {
  private PostDao postDao;

  public Mutation(PostDao postDao) {

    this.postDao = postDao;
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
}
