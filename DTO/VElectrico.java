package DTO;

public class VElectrico extends Vehiculo implements Alquilable {
    private int autonomiaBateria;

    public VElectrico(String matricula, String marca, String modelo, int añoFabricacion, String combustible, int plazas, double precioDia, String estado, int idCategoria, int autonomiaBateria) {
        super(matricula, marca, modelo, añoFabricacion, combustible, plazas, precioDia, estado, idCategoria);
        this.autonomiaBateria = autonomiaBateria;
    }

    public int getAutonomiaBateria() {
        return autonomiaBateria;
    }
    public void setAutonomiaBateria(int autonomiaBateria) {
        this.autonomiaBateria = autonomiaBateria;
    }

    // Método de la interfaz Alquilable
    @Override
    public double calcularPrecio(int dias) {
        double precioBase = dias * getPrecioDia();
        // Ejemplo: los eléctricos tienen un 5% de descuento
        double descuento = precioBase * 0.05;
        return precioBase - descuento;
    }

    @Override
    public String toString() {
        return super.toString() + " | Autonomía batería: " + autonomiaBateria + " km";
    }
}
