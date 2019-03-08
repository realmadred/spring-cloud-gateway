package com.feng.user.dao;

import com.feng.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User, Long> {

    List<User> findByUsername(String username);

    Page<User> findByUsername(String username, Pageable pageable);
}