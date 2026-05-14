package DTO;

public class VElectrico extends Vehiculo implements Alquilable {
	private int autonomia;

	public VElectrico(String matricula, String marca, String modelo, int añoFabricacion, int combustible, int plazas, double precioDia, String estado, int idCategoria, int autonomia) {
		super(matricula, marca, modelo, añoFabricacion, combustible, plazas, precioDia, estado, idCategoria);
		this.autonomia = autonomia;
	}

	public int getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(int autonomia) {
		this.autonomia = autonomia;
	}

	@Override
	public double calcularPrecio(int dias) {
		return getPrecioDia() * dias;
	}
}
