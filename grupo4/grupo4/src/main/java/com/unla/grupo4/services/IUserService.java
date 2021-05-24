package com.unla.grupo4.services;

import java.util.List;

import com.unla.grupo4.entities.User;
import com.unla.grupo4.models.UserModel;

public interface IUserService {
	
	public List<User> getAll();
	
	public List<User> findByEnabled(boolean enabled);
	
	public UserModel insertOrUpdate(UserModel userModel);
	
	public boolean remove(int id);
}
