package com.dbexample.demo.repo;


import com.dbexample.demo.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long>
{
    List<Person> findByLastnameContains(String lastName);
    List<Person> findByLastname(String lastName);
}
