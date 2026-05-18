package DTO;
import java.util.Date;

public class Alquiler {
	private int idAlquiler;
	private int idEmpleado;
	private int idCliente;
	private Date fechaInicio;
	private Date fechaPrevistaDevolucion;
	private Date fechaRealDevolucion;
	private EstadoContrato estadoContrato;
	private double precioTotal;
	
	
	public Alquiler(int idAlquiler, int idEmpleado, int idCliente, Date fechaInicio, Date fechaPrevistaDevolucion,
			Date fechaRealDevolucion, EstadoContrato estadoContrato, double precioTotal) {
		this.idAlquiler = idAlquiler;
		this.idEmpleado = idEmpleado;
		this.idCliente = idCliente;
		this.fechaInicio = fechaInicio;
		this.fechaPrevistaDevolucion = fechaPrevistaDevolucion;
		this.fechaRealDevolucion = fechaRealDevolucion;
		this.estadoContrato = estadoContrato;
		this.precioTotal = precioTotal;
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
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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
	public EstadoContrato getEstadoContrato() {
		return estadoContrato;
	}
	public void setEstadoContrato(EstadoContrato estadoContrato) {
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
		return "Alquiler - " + idAlquiler + " Fecha Inicio :" + fechaInicio + " Fecha Prevista Devolucion: " + fechaPrevistaDevolucion 
				+ " Fecha Real Devolucion: " + fechaRealDevolucion + " Estado: " + estadoContrato + " Precio Total: " + precioTotal + " Id Empleado: " + idEmpleado + " Id Cliente: " + idCliente;
	}
	
	// Calcular precio total del alquiler multiplicando el precio por día por la cantidad de días
	public double calcularPrecioTotal(int dias, double precioDia) {
		return precioTotal = dias * precioDia;
	}
}