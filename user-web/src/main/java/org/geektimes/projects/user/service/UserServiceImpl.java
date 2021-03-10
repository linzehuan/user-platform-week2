package org.geektimes.projects.user.service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.sql.LocalTransactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class UserServiceImpl implements UserService {


    @Resource(name = "bean/UserRepository")
    private UserRepository userRepository;

    @Resource(name = "bean/Validator")
    private Validator validator;

    @Override
    // 默认需要事务
    @LocalTransactional
    public boolean register(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        violations.forEach(c -> {
            System.out.println(c.getMessage());
        });
        if (!violations.isEmpty()) {
             return false;
        }
        // 主调用
        Collection<User> all = userRepository.getAll();
        System.out.println("all1 = " + all);
        if (all.stream().anyMatch((u) -> StringUtils.isNotBlank(u.getEmail()) && u.getEmail().equals(user.getEmail()))) {
            return false;
        }
        all = userRepository.getAll();
        System.out.println("all2 = " + all);

        // after process
        userRepository.save(user);

        return true;
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    @LocalTransactional
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }
}
