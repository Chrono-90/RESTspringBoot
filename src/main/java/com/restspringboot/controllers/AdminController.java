package com.restspringboot.controllers;

import com.restspringboot.entities.User;
import com.restspringboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
    @RequestMapping("/admin")
    public class AdminController {

        private final UserServiceImpl userServiceImpl;

        @Autowired
        private PasswordEncoder passwordEncoder;

        public AdminController(UserServiceImpl userServiceImpl) {
            this.userServiceImpl = userServiceImpl;
        }

        @GetMapping("users")
        public List<User> getAllUsers() {
            return userServiceImpl.getList();
        }

        @PostMapping("/users")
        public void addNewUser(@RequestBody User user) {
            user.setPassword(encoder(user.getPassword()));
            userServiceImpl.save(user);
        }

        @PutMapping("/users/{id}")
        public ResponseEntity<?> updateUser(@RequestBody User user) {
            try {
                user.setPassword(encoder(user.getPassword()));
                userServiceImpl.save(user);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/users/{id}")
        public void deleteUser(@PathVariable Long id) {
            userServiceImpl.remove(id);
        }

        @GetMapping("/users/{id}")
        public ResponseEntity<?> getUserInfo(@PathVariable Long id) {
            try {
                User user = userServiceImpl.getById(id);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        private String encoder(String codeHash) {

            if (codeHash.length() < 60)  {
                codeHash = passwordEncoder.encode(codeHash);
            }
            return codeHash;

        }
    }

