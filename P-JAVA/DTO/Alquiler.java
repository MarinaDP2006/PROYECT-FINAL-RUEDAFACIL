package DTO;
import java.util.Date;

public class Alquiler implements Alquilable {
	private int idAlquiler;
	private Date fechaInicio;
	private Date fechaPrevistaDevolucion;
	private Date fechaRealDevolucion;
	private String estadoContrato;
	private double precioTotal;
	
	public Alquiler(int idAlquiler, Date fechaInicio, Date fechaPrevistaDevolucion, Date fechaRealDevolucion, String estadoContrato, double precioTotal) {
		this.idAlquiler = idAlquiler;
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
	
	public String toString() {
		return "Alquiler - " + idAlquiler + " Fecha Inicio :" + fechaInicio + " Fecha Prevista Devolucion: " + fechaPrevistaDevolucion 
				+ " Fecha Real Devolucion: " + fechaRealDevolucion + " Estado: " + estadoContrato + " Precio Total: " + precioTotal;
	}

	@Override
	public double calcularPrecio(int dias) {
		return 0;
	}	
}
