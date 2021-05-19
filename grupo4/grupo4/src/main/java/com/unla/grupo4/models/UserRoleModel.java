package com.unla.grupo4.models;

public class UserRoleModel {
	
	
	private int id;
	private String role;
	
	public UserRoleModel() { }

	public UserRoleModel(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}	
}
