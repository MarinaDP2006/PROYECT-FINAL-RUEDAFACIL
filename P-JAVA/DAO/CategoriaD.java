package DAO;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.stream.Collectors;
import DTO.Categoria;

public class CategoriaD {
	private HashMap<Integer, Categoria> categoriasMap = new HashMap<>();
	private int proximoId = 1;
	
	// CRUD COMPLETO
	public void crearCategoria(String nombre, String descripcion) {
		List<Categoria> categorias = new ArrayList<>(); // Lista de categorias
		Categoria nuevaCategoria = new Categoria(proximoId++, nombre, descripcion);
		categoriasMap.put(nuevaCategoria.getIdCategoria(), nuevaCategoria);
		categorias.add(nuevaCategoria); // Agregar a la lista
		System.out.println("Categoria nueva: " + nuevaCategoria);	
	}

	public void actualizarCategoria(int idCategoria, String nombre, String descripcion) {
		List<Categoria> categorias = new ArrayList<>(categoriasMap.values()); // Lista de categorias
		for (Categoria cat : categorias) {
			if (cat.getIdCategoria() == idCategoria) { // Se busca por Id y actualiza datos
				cat.setNombre(nombre);
				cat.setDescripcion(descripcion);
				System.out.println("Categoria con ID " + idCategoria + " modificado.");
				return;
			}
		}
	}
	
 	public void eliminarCategoria(int idCategoria) {
 		Iterator<Map.Entry<Integer, Categoria>> iterator = categoriasMap.entrySet().iterator();
 		while (iterator.hasNext()) {
 			Map.Entry<Integer, Categoria> entry = iterator.next();
 			if (entry.getKey() == idCategoria) {
 				iterator.remove();
 				System.out.println("Categoria eliminada: " + entry.getValue());
 				return;
 			}
 		}
 	}
	
	// Buscar categoría por ID
 	public Categoria buscarPorId(int idCategoria) {
 		Categoria resultado = categoriasMap.getOrDefault(idCategoria, null);
 		return resultado; // Devuelve la categoría encontrada o null si no se encuentra
	}
	
	// Listar todas las categorías
	public List<Categoria> listarTodas() {
		List<Categoria> categorias = categoriasMap.values().stream()
				.sorted(Comparator.comparingInt(Categoria::getIdCategoria))
				.collect(Collectors.toList()); // Devuelve una lista de categorías
		return categorias;
	}
	
	// BONUS - Buscar por nombre usando streams
	public List<Categoria> buscarPorNombre(String nombre) {
		return categoriasMap.values().stream()
				.filter(c -> c.getNombre().toUpperCase().contains(nombre.toUpperCase()))
				.sorted(Comparator.comparing(Categoria::getNombre))
				.collect(Collectors.toList());
	}
	
	// BONUS - Obtener TreeSet ordenado por nombre
	public TreeSet<String> obtenerNombresOrdenados() {
		return categoriasMap.values().stream()
				.map(Categoria::getNombre)
				.collect(Collectors.toCollection(TreeSet::new));
	}
	
	// BONUS - Obtener LinkedList
	public LinkedList<Categoria> obtenerEnLinkedList() {
		return categoriasMap.values().stream()
				.sorted(Comparator.comparingInt(Categoria::getIdCategoria))
				.collect(Collectors.toCollection(LinkedList::new));
	}
}
