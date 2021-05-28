package com.unla.grupo4.models;

public enum TypeDoc {
	DNI("DNI"), LC("LC"), LE("LE");

	private final String type;

	private TypeDoc(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
