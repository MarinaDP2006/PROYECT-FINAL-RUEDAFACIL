package DTO;

public class Cliente {
	private String dni;
	private String nombre;
	private String correo;
	private int telefono;
	private String carnetConducir;
	
	public Cliente(String dni, String nombre, String correo, int telefono, String carnetConducir) {
		this.dni = dni;
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
		this.carnetConducir = carnetConducir;
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getCarnetConducir() {
		return carnetConducir;
	}
	public void setCarnetConducir(String carnetConducir) {
		this.carnetConducir = carnetConducir;
	}
	
	@Override
	public String toString() {
		return "Cliente: DNI - " + dni + " Nombre - " + nombre + " Correo - " + correo + " Telefono - " + telefono 
				+ " Carnet de Conducir - " + carnetConducir;
	}
}