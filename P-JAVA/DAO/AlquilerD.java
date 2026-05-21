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
	}
	
	public void actualizarAlquiler(Alquiler alquiler) {
		
	}

	public void eliminarAlquiler(int idAlquiler) {
		
	}

// Buscar un alquiler por su ID 
	public Alquiler buscarPorId(int idAlquiler) {
	
		
		return null; // Devuelve el alquiler encontrado o vacío si no lo encuentra
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
