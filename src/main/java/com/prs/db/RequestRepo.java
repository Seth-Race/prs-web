package com.prs.db;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.Request;

public interface RequestRepo extends CrudRepository<Request, Integer> {

}
