package com.unla.grupo4.models;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class UserRoleModel {
	
	
	private int id;
	
	@NotBlank(message = "el campo rol no debe estar vacio")
	private String role;
	
	public UserRoleModel() { }

	public UserRoleModel(int id, String role) {
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

	@Override
	public String toString() {
		return role;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id == ((UserRoleModel)obj).id;
    }
	
}
