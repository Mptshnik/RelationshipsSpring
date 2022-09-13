package com.dbexample.demo.repo;

import com.dbexample.demo.model.Passport;
import org.springframework.data.repository.CrudRepository;

public interface PassportRepository extends CrudRepository<Passport, Long>
{
}
