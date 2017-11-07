package com.koakh.springbootgraphqlstarter.repository;

import com.koakh.springbootgraphqlstarter.domain.Post;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IPostRepository {

  List<Post> getRecentPosts(int count, int offset);

  List<Post> getAuthorPosts(String author);

  void savePost(Post post);
}
