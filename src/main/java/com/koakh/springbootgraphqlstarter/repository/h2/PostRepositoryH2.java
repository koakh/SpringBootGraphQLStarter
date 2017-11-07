package com.koakh.springbootgraphqlstarter.repository.h2;

import com.koakh.springbootgraphqlstarter.domain.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepositoryH2 extends PagingAndSortingRepository<Post, String> {

  Post findById(String id);
  List<Post> findByAuthorId(String id);
}
