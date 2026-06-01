package DTO;

import java.time.LocalDate;

public class Alquiler {
	private int idAlquiler;
	private int idEmpleado;
	private String idCliente;
	private LocalDate fechaInicio;
	private LocalDate fechaPrevistaDevolucion;
	private LocalDate fechaRealDevolucion;
	private String estadoContrato;
	private double precioTotal;

	public Alquiler(int idAlquiler, int idEmpleado, String idCliente, LocalDate fechaInicio,
			LocalDate fechaPrevistaDevolucion,
			LocalDate fechaRealDevolucion, String estadoContrato, double precioTotal) {
		this.idAlquiler = idAlquiler;
		this.idEmpleado = idEmpleado;
		this.idCliente = idCliente;
		this.fechaInicio = fechaInicio;
		this.fechaPrevistaDevolucion = fechaPrevistaDevolucion;
		this.fechaRealDevolucion = fechaRealDevolucion;
		this.estadoContrato = estadoContrato;
		this.precioTotal = precioTotal;
	}

	// Constructor para crear nuevos alquileres
	public Alquiler() {
	}

	public int getIdAlquiler() {
		return idAlquiler;
	}

	public void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate localDate) {
		this.fechaInicio = localDate;
	}

	public LocalDate getFechaPrevistaDevolucion() {
		return fechaPrevistaDevolucion;
	}

	public void setFechaPrevistaDevolucion(LocalDate fechaPrevistaDevolucion) {
		this.fechaPrevistaDevolucion = fechaPrevistaDevolucion;
	}

	public LocalDate getFechaRealDevolucion() {
		return fechaRealDevolucion;
	}

	public void setFechaRealDevolucion(LocalDate fechaRealDevolucion) {
		this.fechaRealDevolucion = fechaRealDevolucion;
	}

	public String getEstadoContrato() {
		return estadoContrato;
	}

	public void setEstadoContrato(String estadoContrato) {
		this.estadoContrato = estadoContrato;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	@Override
	public String toString() {
		return "Alquiler - " + idAlquiler + " Fecha Inicio :" + fechaInicio + " Fecha Prevista Devolucion: " + fechaPrevistaDevolucion + " Fecha Real: " + fechaRealDevolucion + " Estado: " + estadoContrato
				+ " Precio Total: " + precioTotal + " Id Empleado: " + idEmpleado + " Id Cliente: " + idCliente;
	}
}
