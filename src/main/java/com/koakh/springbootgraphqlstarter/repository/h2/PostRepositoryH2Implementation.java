package com.koakh.springbootgraphqlstarter.repository.h2;

import com.koakh.springbootgraphqlstarter.domain.Post;
import com.koakh.springbootgraphqlstarter.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("h2Repository")
public class PostRepositoryH2Implementation implements IPostRepository {

  private final PostRepositoryH2 repository;

  public PostRepositoryH2Implementation(PostRepositoryH2 repository) {
    this.repository = repository;
  }

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
