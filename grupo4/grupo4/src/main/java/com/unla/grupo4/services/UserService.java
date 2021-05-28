package com.unla.grupo4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo4.converters.UserConverter;
import com.unla.grupo4.entities.User;
import com.unla.grupo4.models.UserModel;
import com.unla.grupo4.repositories.IUserRepository;

@Service("userService")
public class UserService implements IUserService{
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	@Override
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	@Override
	public UserModel findById(int id) {
		return userConverter.entityToModel(userRepository.findById(id).get());
	}
	
	@Override
	public List<User> findByEnabled(boolean enabled){
		return userRepository.findByEnabled(enabled);
	}
	
	@Override
	public User findByDni(String dni) {
		return userRepository.findByDni(dni);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}
	
	@Override
	public User findByUserPassword(String password) {
		return userRepository.findByUserPassword(password);
	}
	
	@Override
	public UserModel insertOrUpdate(UserModel userModel) {
		User user = userRepository.save(userConverter.modelToEntity(userModel));
		return userConverter.entityToModel(user);
	}
	
	@Override
	public boolean remove(int id) {
		try {
			userRepository.findById(id).get().setEnabled(true);
			userRepository.save(userRepository.findById(id).get());
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}
