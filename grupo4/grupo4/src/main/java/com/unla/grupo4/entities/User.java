package com.unla.grupo4.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "user_role_id"))
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_role_id", nullable=false)
	private UserRole role;
	
	@Column(name = "name", nullable = false, length = 45)
	private String name;
	
	@Column(name = "surname", nullable = false, length = 45)
	private String surname;
	
	@Column(name = "dni", nullable = false, length = 8)
	private String dni;
	
	@Column(name = "email", nullable = false, length = 70)
	private String Email;
	
	@Column(name = "user_name", nullable = false, length = 25)
	private String userName;
	
	@Column(name = "user_password", nullable = false, length = 25)
	private String userPassword;
	
	@Column(name = "type_doc")
	private int typeDoc;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name="createdat")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updatedat")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public User() {
		super();
	}

	public User(String name, String surname, String dni, String email, String userName, String userPassword) {
		super();
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.Email = email;
		this.userName = userName;
		this.userPassword = userPassword;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public int getTypeDoc() {
		return typeDoc;
	}

	public void setTypeDoc(int typeDoc) {
		this.typeDoc = typeDoc;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
