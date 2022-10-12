package com.shopping.request;

import java.util.Set;

public class SignupRequest {
	
	String firstName;
	String lastName;
	String username;
	String email;
	String password;
	Long phone;
	Set<String> roles;
	public SignupRequest(String firstName,String lastName, String username, String email, String password,Long phone,
			Set<String> roles) {
		super();
		this.firstName=firstName;
		this.lastName=lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone=phone;
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	
}
