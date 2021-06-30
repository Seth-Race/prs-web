package com.prs.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.LineItem;

public interface LineItemRepo extends CrudRepository<LineItem, Integer>{

	List<LineItem> findProductAndQuantityByRequestId(int id);
	List<LineItem> findAllByRequestId(int id);
	
}
