package com.feng.user.service;

import com.feng.common.entity.PageParam;
import com.feng.user.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(User user);

    Long count();

    void deleteById(Long id);

    User findOne(Long id);

    Iterable<User> findAll();

    Page<User> findByUsername(String username, PageParam pageParam);

    List<User> findByUsername(String username);

    Iterable<User> search(String queryString);
}
