package DAO;
import java.util.List;
import DTO.Categoria;

public class CategoriaD {
	// CRUD COMPLETO
	public void crearCategoria(String categoria) {
		List<Categoria> categorias = new ArrayList<>(); // Lista de categorias
		categorias.add(categorias); // Agregar a la lista
		System.out.println("Categoria nueva: " + ());	
	}

	public void actualizarCategoria(String categoria) {
		List<Categoria> categorias = new ArrayList<>(); // Lista de categorias
		for (Categoria cat : categoria) {
			if () { // Se busca por Id y actualiza datos
				System.out.println("Categoria con ID " +  " modificado.");
				return;
			}
		}
	}
	
 	public void eliminarCategoria(String categoria) {

 	}
	
// Buscar categoría por ID
 	public void buscarPorId(int idCategoria) {

 		return; // Devuelve la categoría encontrada o null si no se encuentra
	}
	
	// Listar todas las categorías
	  	public void listarTodas() {
	  	List<Categoria> categorias = List.of(); // Devuelve una lista de categorías
  	}
}
