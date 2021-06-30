package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.prs.business.LineItem;
import com.prs.business.Request;
import com.prs.db.LineItemRepo;
import com.prs.db.RequestRepo;


@CrossOrigin
@RestController
@RequestMapping("/api/line-items")
public class LineItemController {

	
	@Autowired
	public LineItemRepo lineItemRepo;
	@Autowired
	private RequestRepo requestRepo;
	
	@GetMapping("/")
	public Iterable<LineItem> getAll(){
		return lineItemRepo.findAll();
	}

//	commented out so I can run lines-for-pr.
	@GetMapping("/{id}")
	public Optional<LineItem> get(@PathVariable Integer id){
		return lineItemRepo.findById(id);
	}
	
	@PostMapping("/")
	public LineItem add(@RequestBody LineItem lineItem) {
		LineItem li = lineItemRepo.save(lineItem);
		if (recalculateTotal(li.getRequest())) {
			// successful recalc.
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Exception caught during collection post");
		}
		
		
		return li;
	}
	
	@PutMapping("/")
	public LineItem update(@RequestBody LineItem lineItem) {
		LineItem li = lineItemRepo.save(lineItem);
		if (recalculateTotal(li.getRequest())) {
			// successful recalc.
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Exception caught during collection put");
		}
		
		
		return li;
	}
	
	@DeleteMapping("/{id}")
	public Optional<LineItem> delete(@PathVariable Integer id){
		Optional<LineItem> lineItem = lineItemRepo.findById(id);
		if (lineItem.isPresent()) {
			try {
				lineItemRepo.deleteById(id);
				if (!recalculateTotal(lineItem.get().getRequest())) {
					throw new Exception("Issue recalculating total on delete.");
				} else {
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
							"Exception caught during collection post");
				}
			
		
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
	
	//Custom Queries
	
	// get all lineitems in a request. TR4
	@GetMapping("/lines-for-pr/{id}")
	public List<LineItem> getPr(@PathVariable Integer id) {
		return lineItemRepo.findProductAndQuantityByRequestId(id);
	}
	
	
	
	
	// Recalculation of Total
	private boolean recalculateTotal(Request request) {
		boolean success = false;
		try {
			List<LineItem> lis = lineItemRepo.findAllByRequestId(request.getId());
			double total = 0.0;
			for (LineItem li : lis) {
				total += li.getQuantity() * li.getProduct().getPrice();
			}
			// set new total on user.
			request.setTotal(total);
			requestRepo.save(request);
			success = true;
		} catch (Exception e) {
			System.err.println("Error saving new total.");
			e.printStackTrace();
		}

		return success;
	}
	
}
