package com.feng.user.service;

import com.feng.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(User user);

    User findOne(Long id);

    Iterable<User> findAll();

    Page<User> findByUsername(String username, PageRequest pageRequest);

    List<User> findByUsername(String username);

    Iterable<User> search(String queryString);
}
