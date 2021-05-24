package com.unla.grupo4.models;

enum TypeDoc {
	DNI,
	LE,
	LC
}

public class UserModel {
	
	private int id;
	private String name;
	private String surname;
	private String dni;
	private String email;
	private String userName;
	private String userPassword;
	private UserRoleModel role;
	private TypeDoc typeDoc;
	
	
	public UserModel() {
		super();
	}

	public UserModel(String name, String surname, String dni, String email, String userName, String userPassword,
			UserRoleModel role) {
		super();
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.email = email;
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
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public UserRoleModel getRole() {
		return role;
	}

	public void setRole(UserRoleModel role) {
		this.role = role;
	}

	public TypeDoc getTypeDoc() {
		return typeDoc;
	}

	public void setTypeDoc(TypeDoc typeDoc) {
		this.typeDoc = typeDoc;
	}
	
}
