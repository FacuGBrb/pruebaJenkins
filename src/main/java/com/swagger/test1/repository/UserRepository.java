package com.swagger.test1.repository;

import com.swagger.test1.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long>
{

    User getUserById(Long id);

}
