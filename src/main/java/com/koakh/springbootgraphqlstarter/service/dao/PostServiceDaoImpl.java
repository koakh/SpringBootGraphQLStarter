package com.koakh.springbootgraphqlstarter.service.dao;

import com.koakh.springbootgraphqlstarter.domain.Post;
import com.koakh.springbootgraphqlstarter.service.PostService;

import java.util.List;

public class PostServiceDaoImpl implements PostService {
  @Override
  public List<Post> getRecentPosts(int count, int offset) {
    return null;
  }

  @Override
  public List<Post> getAuthorPosts(String author) {
    return null;
  }

  @Override
  public void savePost(Post post) {

  }
}
