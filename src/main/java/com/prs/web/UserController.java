package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.prs.business.User;
import com.prs.db.UserRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@GetMapping("/")
	public Iterable<User> getAll() {
		return userRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<User> get(@PathVariable Integer id) {
		return userRepo.findById(id);
	}

	@PostMapping("/")
	public User add(@RequestBody User user) {
		return userRepo.save(user);
	}

	@PutMapping("/")
	public User update(@RequestBody User user) {
		return userRepo.save(user);
	}

	@DeleteMapping("/{id}")
	public Optional<User> delete(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			try {
				userRepo.deleteById(id);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Exception caught during user delete");
			}
		} else {
			System.err.println("User delete error - No user found for ID:" + id);
		}

		return user;
	}

}
