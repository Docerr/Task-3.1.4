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

//    @Override
//    @Transactional
//    public Set<Role> getRoleByName(String roleName) {
//        return roleDao.getRoleByName(roleName);
//    }

    @Override
    public Set<Role> findRolesByName(String roleName) {
        Set<Role> roles = new HashSet<>();
        for (Role role : getRoles()) {
            if (roleName.contains(role.getRoleName())) {
                roles.add(role);
            }
        }
        return roles;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }
}
