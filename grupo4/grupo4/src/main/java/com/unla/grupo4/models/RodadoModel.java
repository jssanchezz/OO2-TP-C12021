package com.unla.grupo4.models;

public class RodadoModel {
	
	private int idRodado;
	private String dominio;
	private String vehiculo;
	
	public RodadoModel() {}

	public RodadoModel(int idRodado, String dominio, String vehiculo) {
		super();
		this.idRodado = idRodado;
		this.dominio = dominio;
		this.vehiculo = vehiculo;
	}

	public int getIdRodado() {
		return idRodado;
	}

	public void setIdRodado(int idRodado) {
		this.idRodado = idRodado;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idRodado;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.idRodado == ((RodadoModel)obj).getIdRodado();
	}	
}
