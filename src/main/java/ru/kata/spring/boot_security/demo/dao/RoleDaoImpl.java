package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public Set<Role> getRoleByName(String roleName) {
//        Set<Role> roles = new HashSet<>();
//        for (Role role : getRoles()) {
//            if (roleName.contains(role.getRoleName())) {
//                roles.add(role);
//            }
//        }
//        return roles;
//    }
    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("select r FROM Role r", Role.class).getResultList();

    }
}
