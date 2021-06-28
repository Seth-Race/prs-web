package com.prs.db;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.User;

public interface UserRepo extends CrudRepository<User, Integer>{

}
