package com.koakh.springbootgraphqlstarter.repository;

import com.koakh.springbootgraphqlstarter.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, String> {

  Post findById(String id);
  List<Post> findByAuthorId(String id);
  List<Post> findAll(Pageable pageable);
}
