package com.restspringboot.service;

import com.restspringboot.entities.Role;
import com.restspringboot.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    public  RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public Set<Role> allRoles() {
        return new HashSet<>(roleRepository.findAll());
    }
}
