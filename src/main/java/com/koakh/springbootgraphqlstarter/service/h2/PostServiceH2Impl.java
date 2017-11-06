package com.koakh.springbootgraphqlstarter.service.h2;

import com.koakh.springbootgraphqlstarter.domain.Post;
import com.koakh.springbootgraphqlstarter.repository.PostRepository;
import com.koakh.springbootgraphqlstarter.service.PostService;

import java.util.List;

public class PostServiceH2Impl implements PostService {

  private PostRepository repository;

  @Override
  public List<Post> getRecentPosts(int count, int offset) {
    return (List<Post>) repository.findAll();
  }

  @Override
  public List<Post> getAuthorPosts(String author) {
    return repository.findByAuthorId(author);
  }

  @Override
  public void savePost(Post post) {
    repository.save(post);
  }
}
