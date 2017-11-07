package com.koakh.springbootgraphqlstarter.repository.h2;

import com.koakh.springbootgraphqlstarter.domain.Author;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepositoryH2 extends PagingAndSortingRepository<Author, String> {
}
