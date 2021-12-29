package com.restspringboot.controllers;

import com.restspringboot.entities.Role;
import com.restspringboot.entities.User;
import com.restspringboot.service.RoleServiceImpl;
import com.restspringboot.service.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class ViewController {

    private final RoleServiceImpl roleService;

    private final UserServiceImpl userServiceImpl;

    public ViewController(RoleServiceImpl roleService, UserServiceImpl userServiceImpl) {
        this.roleService = roleService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/admin")
    public String restAdmin(Model model) {
        User user = (User) userServiceImpl.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()) ;
        Set<Role> roles = roleService.allRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "admin";
    }

    @GetMapping("/user")
    public String restUser(Model model) {
        User user = (User) userServiceImpl.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()) ;
        model.addAttribute("user", user);
        return "user";
    }
}
