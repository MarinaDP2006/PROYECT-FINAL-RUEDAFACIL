package DAO;
import java.util.ArrayList;
import java.util.List;
import DTO.Empleado;

public class EmpleadoD {
	    private static List<Empleado> empleados = new ArrayList<>(); // Lista
	// CRUD COMPLETO
    public void crearEmpleado(Empleado empleado) {
        empleados.add(empleado);
        System.out.println("Empleado creado: " + empleado.getNombre());
    }
	
    public void actualizarEmpleado(Empleado empleadoActualizado) {
        for (Empleado e : empleados) {
            if (e.getIdEmpleado() == empleadoActualizado.getIdEmpleado()) {
                e.setNombre(empleadoActualizado.getNombre());
                e.setCargo(empleadoActualizado.getCargo());
                System.out.println("Empleado actualizado: " + e.getIdEmpleado());
                return;
            }
        }
        System.out.println("Empleado no encontrado: " + empleadoActualizado.getIdEmpleado());
    }
	
    public void eliminarEmpleado(int id) {
        Iterator<Empleado> it = empleados.iterator();
        while (it.hasNext()) {
            Empleado e = it.next();
            if (e.getIdEmpleado() == id) {
                it.remove();
                System.out.println("Empleado eliminado: " + id);
                return;
            }
        }
        System.out.println("Empleado no encontrado: " + id);
    }
	
// Buscar empleado por ID/DNI 
    public Empleado buscarPorID(int id) {
        for (Empleado e : empleados) {
            if (e.getIdEmpleado() == id) {
                return e;
            }
        }
        System.out.println("Empleado no encontrado: " + id);
        return null;
    }
	
// Listar todos los empleados
    public void listarTodos() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        System.out.println("Lista de empleados:");
        for (Empleado e : empleados) {
            System.out.println(e);
        }
    }
}
