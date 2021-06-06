package com.unla.grupo4.services;

import java.util.List;

import com.unla.grupo4.entities.User;
import com.unla.grupo4.models.UserModel;

public interface IUserService {
	
	public List<User> getAll();
	
	public List<User> findByEnabled(boolean enabled);
	
	public User findByDni(String dni);
	
	public User findByEmail(String email);
	
	public User findByUserPassword(String password);
	
	public User findByUserName(String username);
	
	public UserModel insertOrUpdate(UserModel userModel);
	
	public UserModel findById(int id);
	
	public boolean remove(int id);
	
	public String getRoleOfUserLog();
}
