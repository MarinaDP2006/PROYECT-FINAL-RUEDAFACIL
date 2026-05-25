package DTO;

public class Vehiculo implements Alquilable {
	private String matricula;
	private String marca;
	private String modelo;
	private int añoFabricacion;
	private String combustible;
	private int plazas;
	private double precioDia;
	private String estado;
	private String categoria;
	
	public Vehiculo(String matricula, String marca, String modelo, int añoFabricacion, String combustible, int plazas, double precioDia, String estado, String categoria) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.añoFabricacion = añoFabricacion;
		this.combustible = combustible;
		this.plazas = plazas;
		this.precioDia = precioDia;
		this.estado = estado;
		this.categoria = categoria;
	}

	public Vehiculo() {
	}

	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getAñoFabricacion() {
		return añoFabricacion;
	}
	public void setAñoFabricacion(int añoFabricacion) {
		this.añoFabricacion = añoFabricacion;
	}
	public String getCombustible() {
		return combustible;
	}
	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}
	public int getPlazas() {
		return plazas;
	}
	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	public double getPrecioDia() {
		return precioDia;
	}
	public void setPrecioDia(double precioDia) {
		this.precioDia = precioDia;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
		
	public String toString() {
		return "Vehiculo: " + matricula + marca + "Modelo - " + modelo + "Fabricación en " + añoFabricacion + " Combustible - " + combustible + " Plazas - " + plazas + " Precio x día - " + precioDia
				+ " Estado - " + estado + " Categoria -" + categoria;
	}

	@Override
// Calcular el precio total del alquiler multiplicando el precio por día por la cantidad de días
	public double calcularPrecio(int dias, int precioDia) { 
		return precioDia * dias;
	}
}