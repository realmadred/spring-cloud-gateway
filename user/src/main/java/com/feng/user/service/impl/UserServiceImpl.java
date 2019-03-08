package com.feng.user.service.impl;

import com.feng.user.dao.UserRepository;
import com.feng.user.entity.User;
import com.feng.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User article) {
        return userRepository.save(article);
    }

    @Override
    public void delete(User article) {
        userRepository.delete(article);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public Page<User> findByUsername(String username, PageRequest pageRequest) {
        return userRepository.findByUsername(username, pageRequest);
    }

    @Override
    public List<User> findByUsername(String title) {
        return userRepository.findByUsername(title);
    }

    @Override
    public Iterable<User> search(String queryString) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
        return userRepository.search(builder);
    }

}