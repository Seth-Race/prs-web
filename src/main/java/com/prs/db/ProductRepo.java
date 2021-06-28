package com.prs.db;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.Product;

public interface ProductRepo extends CrudRepository<Product, Integer> {

}
