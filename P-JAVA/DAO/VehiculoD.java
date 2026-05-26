package DAO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import DTO.Categoria;
import DTO.Vehiculo;

public class VehiculoD {
	
// CRUD COMPLETO
    public void crearVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
        System.out.println("Vehículo creado: " + vehiculo.getMatricula());
    }
	
    public void actualizarVehiculo(Vehiculo vehiculoActualizado) {
        for (Vehiculo v : vehiculos) {
            if (v.getMatricula().equals(vehiculoActualizado.getMatricula())) {
                v.setModelo(vehiculoActualizado.getModelo());
                v.setPrecioDia(vehiculoActualizado.getPrecioDia());
                v.setDisponible(vehiculoActualizado.isDisponible());
                v.setCategoria(vehiculoActualizado.getCategoria());
                System.out.println("Vehículo actualizado: " + v.getMatricula());
                return;
            }
        }
        System.out.println("Vehículo no encontrado: " + vehiculoActualizado.getMatricula());
    }
	
    public void eliminarVehiculo(Vehiculo vehiculo) {
        Iterator<Vehiculo> it = vehiculos.iterator();
        while (it.hasNext()) {
            Vehiculo v = it.next();
            if (v.getMatricula().equals(vehiculo.getMatricula())) {
                it.remove();
                System.out.println("Vehículo eliminado: " + vehiculo.getMatricula());
                return;
            }
        }
        System.out.println("Vehículo no encontrado: " + vehiculo.getMatricula());
    }
	
// Buscar por matrícula
	public static void buscarPorMatricula(String matricula) {
		
	}
	
// Listar todos
	public static void listarTodos() {
		ArrayList<VehiculoD> vehiculos = new ArrayList<>(); // Devuelve una lista de vehículos
	}
	
// Listar  por categoría
    public static void listarPorCategoria(int idCategoria) {
    }
}
