package com.unla.grupo4.converters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.unla.grupo4.models.UserRoleModel;
import com.unla.grupo4.services.IUserRoleService;

@Component("CoverterString")
public class StringToUserRoleConverter implements Converter<String, UserRoleModel>{

	@Autowired
	private IUserRoleService userRole;
	
	@Override
	public UserRoleModel convert(String source) {
		return userRole.findById(Integer.valueOf(source));
	}

}
