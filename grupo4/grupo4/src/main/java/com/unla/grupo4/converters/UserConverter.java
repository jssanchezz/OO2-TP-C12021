package com.unla.grupo4.converters;

import org.springframework.stereotype.Component;

import com.unla.grupo4.entities.User;
import com.unla.grupo4.entities.UserRole;
import com.unla.grupo4.models.TypeDoc;
import com.unla.grupo4.models.UserModel;
import com.unla.grupo4.models.UserRoleModel;

@Component("userConverter")
public class UserConverter {

	public UserModel entityToModel(User user) {
		return new UserModel(user.getName(), user.getSurname(), user.getTypeDoc(), user.getDni(), user.getEmail(),
				user.getUserName(), user.getUserPassword(),
				new UserRoleModel(user.getRole().getId(), user.getRole().getRole()));
	}

	public User modelToEntity(UserModel userModel) {
		return new User(userModel.getName(), userModel.getSurname(), userModel.getTypeDoc(), userModel.getDni(),
				userModel.getEmail(), userModel.getUserName(), userModel.getUserPassword(),
				new UserRole(userModel.getRole().getId(), userModel.getRole().getRole()));
	}

}
