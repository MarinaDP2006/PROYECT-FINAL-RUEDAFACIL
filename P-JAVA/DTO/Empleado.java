package DTO;

public class Empleado {
	private int idEmpleado;
	private String nombre;
	private Cargo cargo;
	private String oficina;
	private String turno;
	private int añosExp;
	
	public Empleado(int idEmpleado, String nombre, Cargo cargo, String oficina, String turno, int añosExp) {
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.cargo = cargo;
		this.oficina = oficina;
		this.turno = turno;
		this.añosExp = añosExp;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public int getAñosExp() {
		return añosExp;
	}
	public void setAñosExp(int añosExp) {
		this.añosExp = añosExp;
	}	
	
	@Override
	public String toString() {
		return "Empleado - ID: " + idEmpleado + " Nombre: " + nombre + " Cargo: " + cargo + " Oficina: " + oficina + " Turno: " + turno + " Años de Experiencia: " + añosExp;
	}	
}