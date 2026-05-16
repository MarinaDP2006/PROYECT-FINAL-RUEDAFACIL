package DTO;

import java.util.Date;

public class Alquiler {
	private int idAlquiler;
	private Date fechaInicio;
	private Date fechaPrevistaDevolucion;
	private Date fechaRealDevolucion;
	private String estadoContrato;
	private double precioTotal;
	private Cliente cliente;
	private Empleado empleado;

	public Alquiler(int idAlquiler, Date fechaInicio, Date fechaPrevistaDevolucion, Date fechaRealDevolucion,
			String estadoContrato, double precioTotal, Cliente cliente, Empleado empleado) {
		this.idAlquiler = idAlquiler;
		this.fechaInicio = fechaInicio;
		this.fechaPrevistaDevolucion = fechaPrevistaDevolucion;
		this.fechaRealDevolucion = fechaRealDevolucion;
		this.estadoContrato = estadoContrato;
		this.precioTotal = precioTotal;
		this.cliente = cliente;
		this.empleado = empleado;
	}

	public int getIdAlquiler() {
		return idAlquiler;
	}

	public void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaPrevistaDevolucion() {
		return fechaPrevistaDevolucion;
	}

	public void setFechaPrevistaDevolucion(Date fechaPrevistaDevolucion) {
		this.fechaPrevistaDevolucion = fechaPrevistaDevolucion;
	}

	public Date getFechaRealDevolucion() {
		return fechaRealDevolucion;
	}

	public void setFechaRealDevolucion(Date fechaRealDevolucion) {
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String toString() {
		return "Alquiler - " + idAlquiler + " Fecha Inicio :" + fechaInicio + " Fecha Prevista Devolucion: "
				+ fechaPrevistaDevolucion
				+ " Fecha Real Devolucion: " + fechaRealDevolucion + " Estado: " + estadoContrato + " Precio Total: "
				+ precioTotal + "Cliente: " + cliente + "Empleado a cargo: " + empleado;
	}
}
