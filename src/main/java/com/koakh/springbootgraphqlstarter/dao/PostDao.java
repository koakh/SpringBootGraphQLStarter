package com.koakh.springbootgraphqlstarter.dao;

import com.koakh.springbootgraphqlstarter.domain.Post;
import com.koakh.springbootgraphqlstarter.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

public class PostDao implements PostService {
  private List<Post> posts;

  public PostDao(List<Post> posts) {
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
