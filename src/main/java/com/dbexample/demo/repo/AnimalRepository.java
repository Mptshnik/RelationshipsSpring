package com.dbexample.demo.repo;

import com.dbexample.demo.model.Animal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Long>
{
    List<Animal> findByNameContains(String name);
    List<Animal> findByName(String name);
}
