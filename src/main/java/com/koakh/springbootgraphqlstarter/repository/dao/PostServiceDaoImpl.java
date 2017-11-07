package com.koakh.springbootgraphqlstarter.repository.dao;

import com.koakh.springbootgraphqlstarter.domain.Post;
import com.koakh.springbootgraphqlstarter.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Qualifier("daoRepository")
public class PostServiceDaoImpl implements IPostRepository {

  private final List<Post> posts;

  public PostServiceDaoImpl(List<Post> posts) {

    this.posts = posts;
  }

  @Override
  public List<Post> getRecentPosts(int count, int offset) {
    return posts.stream()
      .skip(offset)
      .limit(count)
      .collect(Collectors.toList());
  }

  @Override
  public List<Post> getAuthorPosts(String author) {
    return posts.stream()
      .filter(post -> author.equals(post.getAuthorId()))
      .collect(Collectors.toList());
  }

  @Override
  public void savePost(Post post) {
    posts.add(0, post);
  }
}
