package com.dbexample.demo.repo;


import com.dbexample.demo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>
{
    List<User> findByLastnameContains(String lastName);
    List<User> findByLastname(String lastName);
}
