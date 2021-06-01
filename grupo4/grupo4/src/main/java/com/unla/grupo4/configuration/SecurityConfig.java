package com.unla.grupo4.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.unla.grupo4.services.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/*", "/js/*", "/imgs/*",
		"/vendor/*","/eastereggs/*", "/rodado/*","/permiso/*").permitAll()
		.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/login").loginProcessingUrl("/loginprocess")
			.usernameParameter("username").passwordParameter("password")
			.defaultSuccessUrl("/loginsuccess",true).permitAll()
		.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/logout").permitAll();
	}
}
