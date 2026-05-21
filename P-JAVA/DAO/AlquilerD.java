package DAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import DTO.Alquiler;

public class AlquilerD {
	// HashMap para almacenar alquileres con su ID como clave
	private HashMap<Integer, Alquiler> alquileres = new HashMap<>();
	
// CRUD COMPLETO
	public void crearAlquiler(Alquiler alquiler) {
		alquileres.put(alquiler.getIdAlquiler(), alquiler); // En el HashMap
		System.out.println("Alquiler creado: ID " + alquiler.getIdAlquiler() + 
		                   " | Empleado: " + alquiler.getIdEmpleado() + 
		                   " | Cliente: " + alquiler.getIdCliente() + 
		                   " | Fecha inicio: " + alquiler.getFechaInicio());
	}
	
	public void actualizarAlquiler(Alquiler alquiler) {
		if (alquileres.containsKey(alquiler.getIdAlquiler())) { // Si existe en el HashMap
			alquileres.put(alquiler.getIdAlquiler(), alquiler); // Añade los cambios
			System.out.println("Alquiler actualizado: ID " + alquiler.getIdAlquiler());
		} else {
			System.out.println("Alquiler no encontrado con ID: " + alquiler.getIdAlquiler());
		}
	}

	public void eliminarAlquiler(int idAlquiler) {
		Iterator<Integer> iterator = alquileres.keySet().iterator(); // Elimina un alquiler del HashMap de forma segura usando Iterator
		while (iterator.hasNext()) {
			Integer id = iterator.next(); // Itera/pasa por la id
			// Itera a través de las claves del HashMap y elimina la que coincida con el ID
			if (id.equals(idAlquiler)) {
				iterator.remove();
				System.out.println("Alquiler con ID " + idAlquiler + " eliminado.");
				return;
			}
		}
		System.out.println("Alquiler no encontrado con ID: " + idAlquiler);
	}
	
	// Busca un alquiler específico en el HashMap por su ID, devuelve el alquiler encontrado o null si no existe
	public Alquiler buscarPorId(int idAlquiler) {
		return alquileres.getOrDefault(idAlquiler, null);
	}

	// Lista todos los alquileres almacenados. Convierte los valores del HashMap a un ArrayList
  	public List<Alquiler> listarTodos() {
		List<Alquiler> lista = new ArrayList<>(alquileres.values());
		return lista;
	}

	// Consulta todos los alquileres de un cliente específico usando su DNI. 
	public List<Alquiler> consultarPorCliente(String dni) {
		return alquileres.values().stream()
			// Encontrar alquileres que coincidan con el DNI
			.filter(alquiler -> alquiler.getCliente().getDni().equals(dni))
			.collect(Collectors.toList());
	}

	// Consulta alquileres dentro de un rango de fechas específico
	public List<Alquiler> consultarPorFechas(Date inicio, Date fin) {
		return alquileres.values().stream()
			.filter(alquiler -> alquiler.getFechaInicio().compareTo(inicio) >= 0 && alquiler.getFechaInicio().compareTo(fin) <= 0) // Si coincide con las fechas
			.sorted((a1, a2) -> a1.getFechaInicio().compareTo(a2.getFechaInicio())) // Ordena los resultados por fecha de inicio de menor a mayor
			.collect(Collectors.toList()); 
	}

	// Actualiza la información de devolución de un alquiler.
	public void actualizarDevolucion(int idAlquiler, Date fechaReal, Double nuevoPrecio) {
		// Verifica si el alquiler existe antes de actualizar
		if (alquileres.containsKey(idAlquiler)) {
			Alquiler alquiler = alquileres.get(idAlquiler);
			// Modifica la fecha real de devolución y el precio final
			alquiler.setFechaDevolucionReal(fechaReal);
			alquiler.setPrecioFinal(nuevoPrecio);
			System.out.println("Devolución actualizada: ID " + idAlquiler + " | Fecha devolución: " + fechaReal + " | Precio final: " + nuevoPrecio);
		} else {
			System.out.println("Alquiler no encontrado con ID: " + idAlquiler);
		}
	}
}
