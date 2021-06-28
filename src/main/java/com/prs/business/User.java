package com.prs.business;

import javax.persistence.*;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String lastName;
	private String firstName;
	private String email;
	private String phone;
	private boolean reviewer;
	private boolean admin;
	
	
	
	public User(int id, String username, String password, String lastName, String firstName, String email,
			String phone, boolean reviewer, boolean admin) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
		this.reviewer = reviewer;
		this.admin = admin;
	}
	public User() {
		super();
	}
	
	
	
	
	
	public User(String username, String password, String lastName, String firstName, String email, String phone,
			boolean reviewer, boolean admin) {
		super();
		this.username = username;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
		this.reviewer = reviewer;
		this.admin = admin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean getReviewer() {
		return reviewer;
	}
	public void setReviewer(boolean reviewer) {
		this.reviewer = reviewer;
	}
	public boolean getAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
	
	public String getName() {
		return firstName+ " " +lastName;
	}
	
}
