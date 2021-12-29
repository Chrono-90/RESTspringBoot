package com.restspringboot.controllers;

import com.restspringboot.entities.User;
import com.restspringboot.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> UserInfo(@PathVariable Long id) {
        try {
            User user = userServiceImpl.getById(id);
            return new ResponseEntity<>(user ,HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}