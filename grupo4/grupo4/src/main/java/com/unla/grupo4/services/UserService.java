package com.unla.grupo4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.grupo4.repositories.IUserRepository;

@Service("userService")
public class UserService implements UserDetailsService{
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		com.unla.grupo4.entities.User user = userRepository.findByUserName(userName);
		UserBuilder builder = null;
		
		if(user != null) {
			System.out.println("user:"+user.getName());
			builder = User.withUsername(userName);
			builder.disabled(false);
			builder.password(user.getUserPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
		}
		else{
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return builder.build();
	}
}
