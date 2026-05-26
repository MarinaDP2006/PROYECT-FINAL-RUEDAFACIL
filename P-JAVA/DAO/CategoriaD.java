package DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DTO.Categoria;

public class CategoriaD {
    // Lista en memoria de categorías
    private static List<Categoria> categorias = new ArrayList<>();
    private static int nextId = 1; // ID auto

	// CRUD COMPLETO
    public static void crearCategoria(String nombre) {
        Categoria nueva = new Categoria(nextId++, nombre); // Crea categoría con ID único
        categorias.add(nueva);
        System.out.println("Categoría creada: " + nueva);
    }

    public void actualizarCategoria(int id, String nuevoNombre) {
        for (Categoria c : categorias) { // Recorre la lista
            if (c.getIdCategoria() == id) { // Coincidencia por ID
                c.setNombre(nuevoNombre); // Actualiza nombre
                System.out.println("Categoría modificada.");
                return;
            }
        }
        System.out.println("Categoría no encontrada.");
    }

    public void eliminarCategoria(int id) {
        Iterator<Categoria> it = categorias.iterator();
        while (it.hasNext()) {
            Categoria c = it.next();
            if (c.getIdCategoria() == id) { // Coincidencia
                it.remove(); // Elimina
                System.out.println("Categoría eliminada.");
                return;
            }
        }
        System.out.println("Categoría no encontrada.");
    }

    // Buscar categoría por ID
    public Categoria buscarPorId(int id) {
        for (Categoria c : categorias) { // Recorre las categorias
            if (c.getIdCategoria() == id) {
                return c;
            }
        }
        return null;
    }

    // Listar todas las categorías
    public List<Categoria> listarTodas() {
        return categorias; // Devuelve la lista completa
    }
}
