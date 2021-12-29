package com.restspringboot.service;

import com.restspringboot.entities.User;

import java.util.List;

public interface UserService {

   User loadUserByUsername (String username);

    List<User> getList();
    User save(User user);

    User getById(Long id);

    void remove(Long id);
}
