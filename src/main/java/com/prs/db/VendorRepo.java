package com.prs.db;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.Vendor;

public interface VendorRepo extends CrudRepository<Vendor, Integer> {

}
