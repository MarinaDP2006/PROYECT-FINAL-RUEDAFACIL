package DAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import DTO.Alquiler;

// PARA LOS METODOS INDICADOS SE USARÁN:
// HashMap, TreeSet, ArrayList, LinkedList y uso de streams filter(), map(), sorted(), collect(), Comparable y Comparator e Iteradores para eliminación 

public class AlquilerD {
	// Estructura de datos: HashMap para almacenar alquileres con su ID como clave
	private HashMap<Integer, Alquiler> alquileres = new HashMap<>();
	
// CRUD COMPLETO
	public void crearAlquiler(Alquiler alquiler) {
		alquileres.put(alquiler.getIdAlquiler(), alquiler);
		System.out.println("Alquiler creado: ID " + alquiler.getIdAlquiler() + 
		                   " | Empleado: " + alquiler.getIdEmpleado() + 
		                   " | Cliente: " + alquiler.getIdCliente() + 
		                   " | Fecha inicio: " + alquiler.getFechaInicio());
	}
	
	public void actualizarAlquiler(Alquiler alquiler) {
		if (alquileres.containsKey(alquiler.getIdAlquiler())) {
			alquileres.put(alquiler.getIdAlquiler(), alquiler);
			System.out.println("Alquiler actualizado: ID " + alquiler.getIdAlquiler());
		} else {
			System.out.println("Alquiler no encontrado con ID: " + alquiler.getIdAlquiler());
		}
	}

	public void eliminarAlquiler(int idAlquiler) {
		// Usar Iterator para eliminar de forma segura
		Iterator<Integer> iterator = alquileres.keySet().iterator();
		while (iterator.hasNext()) {
			Integer id = iterator.next();
			if (id.equals(idAlquiler)) {
				iterator.remove();
				System.out.println("Alquiler con ID " + idAlquiler + " eliminado.");
				return;
			}
		}
		System.out.println("Alquiler no encontrado con ID: " + idAlquiler);
	}
	
// Buscar un alquiler por su ID 
	public Alquiler buscarPorId(int idAlquiler) {
		return alquileres.getOrDefault(idAlquiler, null);
	}

// Listar todos los alquileres
  	public List<Alquiler> listarTodos() {
		List<Alquiler> lista = new ArrayList<>(alquileres.values());
		return lista;
	}

// Listar los alquileres de un cliente específico por su DNI
	public List<Alquiler> consultarPorCliente(String dni) {
		return alquileres.values().stream()
			.filter(alquiler -> alquiler.getCliente().getDni().equals(dni))
			.collect(Collectors.toList());
	}

// Listar los alquileres dentro de un rango de fechas
	public List<Alquiler> consultarPorFechas(Date inicio, Date fin) {
		return alquileres.values().stream()
			.filter(alquiler -> alquiler.getFechaInicio().compareTo(inicio) >= 0 &&
			                    alquiler.getFechaInicio().compareTo(fin) <= 0)
			.sorted((a1, a2) -> a1.getFechaInicio().compareTo(a2.getFechaInicio()))
			.collect(Collectors.toList());
	}

// Actualizar la información de devolución de un alquiler
	public void actualizarDevolucion(int idAlquiler, Date fechaReal, Double nuevoPrecio) {
		if (alquileres.containsKey(idAlquiler)) {
			Alquiler alquiler = alquileres.get(idAlquiler);
			alquiler.setFechaDevolucionReal(fechaReal);
			alquiler.setPrecioFinal(nuevoPrecio);
			System.out.println("Devolución actualizada: ID " + idAlquiler + 
			                   " | Fecha devolución: " + fechaReal + 
			                   " | Precio final: " + nuevoPrecio);
		} else {
			System.out.println("Alquiler no encontrado con ID: " + idAlquiler);
		}
	}
}
