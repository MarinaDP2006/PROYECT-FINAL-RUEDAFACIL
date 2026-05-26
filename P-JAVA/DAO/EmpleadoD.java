package DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DTO.Empleado;

public class EmpleadoD {
    // Lista en memoria de empleados
    private static List<Empleado> empleados = new ArrayList<>();

	// CRUD COMPLETO
    public void crearEmpleado(Empleado empleado) {
        empleados.add(empleado); // Añade a la lista
        System.out.println("Empleado creado: " + empleado.getNombre());
    }

    public void actualizarEmpleado(Empleado empActualizado) {
        for (Empleado e : empleados) { // Recorre la lista
            if (e.getIdEmpleado() == empActualizado.getIdEmpleado()) { // Coincidencia por ID
                e.setNombre(empActualizado.getNombre());
                e.setCargo(empActualizado.getCargo());
                System.out.println("Empleado actualizado.");
                return;
            }
        }
        System.out.println("Empleado no encontrado.");
    }

    public void eliminarEmpleado(int id) {
        Iterator<Empleado> it = empleados.iterator();
        while (it.hasNext()) {
            Empleado e = it.next();
            if (e.getIdEmpleado() == id) { // Coincidencia
                it.remove(); // Elimina
                System.out.println("Empleado eliminado.");
                return;
            }
        }
        System.out.println("Empleado no encontrado.");
    }

    // Buscar empleado por ID
    public Empleado buscarPorID(int id) {
        for (Empleado e : empleados) {
            if (e.getIdEmpleado() == id) { // Coincidencia
                return e;
            }
        }
        return null;
    }

    // Listar todos los empleados
    public void listarTodos() {
        if (empleados.isEmpty()) { // Si no hay empleados
            System.out.println("No hay empleados registrados.");
            return;
        }
        for (Empleado e : empleados) {
            System.out.println(e);
        }
    }
}
