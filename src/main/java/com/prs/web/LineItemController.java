package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.prs.business.LineItem;
import com.prs.db.LineItemRepo;


@CrossOrigin
@RestController
@RequestMapping("/api/lineitems")
public class LineItemController {

	
	@Autowired
	public LineItemRepo lineItemRepo;
	
	@GetMapping("/")
	public Iterable<LineItem> getAll(){
		return lineItemRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<LineItem> get(@PathVariable Integer id){
		return lineItemRepo.findById(id);
	}
	
	@PostMapping("/")
	public LineItem add(@RequestBody LineItem lineItem) {
		return lineItemRepo.save(lineItem);
	}
	
	@PutMapping("/")
	public LineItem update(@RequestBody LineItem lineItem) {
		return lineItemRepo.save(lineItem);
	}
	
	@DeleteMapping("/{id}")
	public Optional<LineItem> delete(@PathVariable Integer id){
		Optional<LineItem> lineItem = lineItemRepo.findById(id);
		if (lineItem.isPresent()) {
			try {
				lineItemRepo.deleteById(id);
			} catch (DataIntegrityViolationException dive) {
				// catch exception when vendor exists as FK for another table
				System.err.println(dive.getRootCause().getMessage());
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Foreign Key Constraint Issue - Line Item ID:" + id + " is referred to elsewhere.");
			} catch (Exception e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Exception caught during line item delete");
			}
		} else {
			System.err.println("Product delete error - No line item found for ID:" + id);
		}
		return lineItem;
	}
	
}
