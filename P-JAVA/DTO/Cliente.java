package DTO;

public class Cliente {
	private String dni;
	private String nombre;
	private String correo;
	private String telefono;
	private String direccion;
	private String carnetConducir;
	
	public Cliente(String dni, String nombre, String correo, String telefono, String direccion, String carnetConducir) {
		this.dni = dni;
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
		this.direccion = direccion;
		this.carnetConducir = carnetConducir;
	}

	public Cliente() {
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCarnetConducir() {
		return carnetConducir;
	}
	public void setCarnetConducir(String carnetConducir) {
		this.carnetConducir = carnetConducir;
	}

	@Override
	public String toString() {
		return "Cliente: DNI - " + dni + " Nombre - " + nombre + " Telefono - " + telefono +  " Correo - " + correo +  "Direccion - " + direccion + " Carnet de Conducir - " + carnetConducir;
	}
}