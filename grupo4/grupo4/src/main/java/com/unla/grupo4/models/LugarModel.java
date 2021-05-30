package com.unla.grupo4.models;

public class LugarModel {
	private int id;
	private String lugar;
	private String codigoPostal;
	
	public LugarModel() {}
	
	public LugarModel(int id, String lugar, String codigoPostal) {
		this.id = id;
		this.lugar = lugar;
		this.codigoPostal = codigoPostal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.id == ((LugarModel)obj).getId();
	}
}
