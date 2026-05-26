package DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DTO.Categoria;
import DTO.Vehiculo;

public class VehiculoD {
    // Lista en memoria de vehículos
    private static List<Vehiculo> vehiculos = new ArrayList<>();

	// CRUD COMPLETO
    public void crearVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo); // Añade a la lista
        System.out.println("Vehículo creado: " + vehiculo.getMatricula());
    }

    public void actualizarVehiculo(Vehiculo vehActualizado) {
        for (Vehiculo v : vehiculos) { // Recorre la lista
            if (v.getMatricula().equals(vehActualizado.getMatricula())) { // Coincidencia por matrícula
                v.setModelo(vehActualizado.getModelo());
                v.setPrecioDia(vehActualizado.getPrecioDia());
                v.setDisponible(vehActualizado.isDisponible());
                v.setCategoria(vehActualizado.getCategoria());
                System.out.println("Vehículo actualizado.");
                return;
            }
        }
        System.out.println("Vehículo no encontrado.");
    }

    public void eliminarVehiculo(Vehiculo vehiculo) {
        Iterator<Vehiculo> it = vehiculos.iterator();
        while (it.hasNext()) {
            Vehiculo v = it.next();
            if (v.getMatricula().equals(vehiculo.getMatricula())) { // Coincidencia
                it.remove(); // Elimina
                System.out.println("Vehículo eliminado.");
                return;
            }
        }
        System.out.println("Vehículo no encontrado.");
    }

    // Buscar vehículo por matrícula
    public static Vehiculo buscarPorMatricula(String matricula) {
        for (Vehiculo v : vehiculos) {
            if (v.getMatricula().equals(matricula)) { // Coincidencia
                return v;
            }
        }
        return null;
    }

    // Listar todos los vehículos
    public static void listarTodos() {
        if (vehiculos.isEmpty()) { // Si no hay vehículos
            System.out.println("No hay vehículos registrados.");
            return;
        }
        for (Vehiculo v : vehiculos) {
            System.out.println(v);
        }
    }

    // Listar vehículos por categoría
    public static void listarPorCategoria(Categoria categoria) {
        boolean encontrado = false;
        for (Vehiculo v : vehiculos) {
            if (v.getCategoria().getIdCategoria() == categoria.getIdCategoria()) { // Coincidencia por categoría
                System.out.println(v);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay vehículos en esta categoría.");
        }
    }
}
