package com.prs.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.Request;

public interface RequestRepo extends CrudRepository<Request, Integer> {

//	public List<Request> changeByRequestIDForReview(int id);
	
	public List<Request> findByUserIdNotAndStatus(int id, String status);
}
