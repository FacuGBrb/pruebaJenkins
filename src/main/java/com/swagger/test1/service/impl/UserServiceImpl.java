package com.swagger.test1.service.impl;

import com.swagger.test1.model.User;
import com.swagger.test1.repository.UserRepository;
import com.swagger.test1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService
{

    @Autowired
    @Qualifier("userRepository")
    UserRepository userRepository;

    public Iterable findAll() {
        return userRepository.findAll();
    }

    public User find(Long id) {
        return userRepository.getUserById(id);
    }

    public void delete(Long id) {
        userRepository.delete(userRepository.getUserById(id));
    }

    public void save(User user) {
        userRepository.save(user);
    }

}
