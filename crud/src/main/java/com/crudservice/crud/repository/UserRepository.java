package com.crudservice.crud.repository;

import org.springframework.data.repository.CrudRepository;
import com.crudservice.crud.db.User;

public interface UserRepository extends CrudRepository<User, Long>{
}
