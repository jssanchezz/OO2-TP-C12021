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
	
	public List<User> findByEnabled(boolean enabled){
		return userRepository.findByEnabled(enabled);
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
			UserModel user = userConverter.entityToModel(userRepository.findById(id).get());
			insertOrUpdate(user);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}
