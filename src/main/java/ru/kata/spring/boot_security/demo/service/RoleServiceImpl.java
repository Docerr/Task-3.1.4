package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> findRolesByName(String name) {
        Set<Role> roles = new HashSet<>();
        for (Role role : getRoles()) {
            if (name.contains(role.getName())) {
                roles.add(role);
            }
        }
        return roles;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getRoles() {
        return roleDao.getRoles();
    }
}
