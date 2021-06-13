package com.unla.grupo4.models;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class UserRoleModel {
	
	
	private int id;
	
	@NotBlank(message = "El campo no debe estar vacio")
	private String role;
	
	private boolean enabled = true;
	
	public UserRoleModel() { }

	public UserRoleModel(int id, String role, boolean enabled) {
		this.id = id;
		this.role = role;
		this.enabled = enabled;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return role;
	}
}
