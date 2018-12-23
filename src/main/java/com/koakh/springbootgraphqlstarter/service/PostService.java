package com.koakh.springbootgraphqlstarter.service;

import com.koakh.springbootgraphqlstarter.domain.Post;
import com.koakh.springbootgraphqlstarter.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

  private final IPostRepository repository;

  public PostService(@Qualifier("h2Repository") IPostRepository repository) {
    this.repository = repository;
  }

  public List<Post> getRecentPosts(int count, int offset) {
    return repository.getRecentPosts(count, offset);
  }

  public void savePost(Post post) {
    repository.savePost(post);
  }

  public List<Post> getAuthorPosts(String id) {
    return repository.getAuthorPosts(id);
  }
}
