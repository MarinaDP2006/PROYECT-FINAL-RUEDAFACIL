package DAO;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;
import DTO.Categoria;

public class CategoriaD {
	private static HashMap<Integer, Categoria> categoriasMap = new HashMap<>();
	private static int proximoId = 1; // id automatico para nuevas categorías
	
	// CRUD COMPLETO
	public static void crearCategoria(String nombre) {
		List<Categoria> categorias = new ArrayList<>(); // Lista de categorias
		Categoria nuevaCategoria = new Categoria(proximoId++, nombre);
		categoriasMap.put(nuevaCategoria.getIdCategoria(), nuevaCategoria);
		categorias.add(nuevaCategoria); // Agregar a la lista
		System.out.println("Categoria nueva: " + nuevaCategoria);	
	}

	public void actualizarCategoria(int idCategoria, String nombre, String descripcion) {
		List<Categoria> categorias = new ArrayList<>(categoriasMap.values()); // Lista de categorias
		for (Categoria cat : categorias) {
			if (cat.getIdCategoria() == idCategoria) { // Se busca por Id y actualiza datos
				cat.setNombre(nombre);
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
	
		// CONSULTA: Buscar categoría por ID
    public static void listarCategoriasBD() {
        String sql = "SELECT * FROM categorias";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Nombre: " + rs.getString("nombre")
                );
            }
        } catch (Exception e) {
            System.out.println("Error consultando categorías: " + e.getMessage());
        }
    }
}	

	// Listar todas las categorías
	public List<Categoria> listarTodas() {
		List<Categoria> categorias = categoriasMap.values().stream()
				.sorted(Comparator.comparingInt(Categoria::getIdCategoria))
				.collect(Collectors.toList()); // Devuelve una lista de categorías
		return categorias;
	}
}
