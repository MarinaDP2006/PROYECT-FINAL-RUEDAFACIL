package DAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import DTO.Alquiler;

// PARA LOS METODOS INDICADOS SE USARÁN:
// HashMap, TreeSet, ArrayList, LinkedList y uso de streams filter(), map(), sorted(), collect(), Comparable y Comparator e Iteradores para eliminación 

public class AlquilerD {
// CRUD COMPLETO
	public void crearAlquiler(Alquiler alquiler) {
	List<Alquiler> alquileres = new ArrayList<>(); // Lista de alquileres
	alquileres.add(alquiler) // Agregar a la lista
	System.out.println("Alquiler creado: " + alquiler.getIdAlquiler(), + "Empleado a cargo: " + alquiler.getIdEmpleado(), + "Cliente: " + alquiler.getIdCliente() + "Fecha: " + alquiler.getFechaInicio(), + "Prevista devolución: " + alquiler.getFechaPrevistaDevolucion(), + "Fecha real devolución: " + alquiler.getFechaRealDevolucion(), + "Contrato: " + alquiler.getEstadoContrato());		
	}
	
	public void actualizarAlquiler(Alquiler alquiler) {
		
	}
 
	public void eliminarAlquiler(int idAlquiler) {  // Iterator para eliminar de la lista mientras se itera
		Iterator<Alquiler> iterator = new ArrayList<Alquiler>().iterator(); // Lista de alquileres
		while (iterator.hasNext()) {
			// Mientras que haya alquileres en la lista, se obtiene el siguiente
			Alquiler alquiler = iterator.next();
			if (alquiler.getIdAlquiler().equals(id)) { // Se elimina por id
				iterator.remove(); // Eliminar de la lista
				System.out.println("Alquiler con id " + id + " eliminado.");
				return;
			}
		}
	}
	
// Buscar un alquiler por su ID 
	public Alquiler buscarPorId(int idAlquiler) {
			List<Alquiler> alquileres = new ArrayList<>(); 
		for (Alquiler alquiler : alquileres) {
			if (alquiler.getIdAlquiler().equals(id)) { // Se busca por id
				return alquiler; // Devuelve el encontrado
			}
		}
		return null; // Devuelve null si no se encuentra
}

// Listar todos los alquileres
  	public List<Alquiler> listarTodos() {
		List<Alquiler> alquileres = new ArrayList<>();
		return alquileres;
	}

// Listar los alquileres de un cliente específico por su DNI
	public void consultarPorCliente(String dni) {
		
	}

// Listar los alquileres dentro de un rango de fechas
	public void consultarPorFechas(Date inicio, Date fin) {
		
	}

// Actualizar la información de devolución de un alquiler
	public void actualizarDevolucion(int idAlquiler, Date fechaReal, Double nuevoPrecio) {
	}
}
