package com.koakh.springbootgraphqlstarter.service;

import com.koakh.springbootgraphqlstarter.domain.Post;

import java.util.List;

public interface PostService {

  List<Post> getRecentPosts(int count, int offset);

  List<Post> getAuthorPosts(String author);

  void savePost(Post post);
}
