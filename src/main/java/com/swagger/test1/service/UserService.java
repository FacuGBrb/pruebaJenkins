package com.swagger.test1.service;

import com.swagger.test1.model.User;

public interface UserService
{

    Iterable findAll();
    User find(Long id);
    void delete(Long id);
    void save(User user);

}
