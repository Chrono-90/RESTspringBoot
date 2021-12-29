package com.restspringboot.service;


import com.restspringboot.entities.User;
import com.restspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        return user;
    }
    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        return userRepository.findAll();
    }
    @Override
    @Transactional
    public User save(User user) {
        userRepository.save(user);
        return user;
    }
    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
    @Override
    @Transactional
    public void remove(Long id) {
        userRepository.delete(getById(id));
    }
}
