package DAO;

import DTO.Categoria;
import java.sql.*;
import java.util.*;
import src.ConexionBD;

public class CategoriaD {
    private Connection getConnection() {
        return ConexionBD.conectar();
    }

    // CRUD COMPLETO
    public void crearCategoria(Categoria categoria) {
        String sql = "INSERT INTO Categoria (nombre_categoria) VALUES (?)";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria.getNombre());
            pstmt.executeUpdate();
            System.out.println("categoría añadida con éxito.");
        } catch (SQLException e) {
            System.err.println("error al crear categoría: " + e.getMessage());
        }
    }

    public void modificarCategoria(Categoria categoria) {
        String sql = "UPDATE Categoria SET nombre_categoria = ? WHERE id_categoria = ?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria.getNombre());
            pstmt.setInt(2, categoria.getIdCategoria());
            int filas = pstmt.executeUpdate();
            if (filas > 0)
                System.out.println("categoría modificada.");
            else
                System.out.println("no se encontró categoría con ese id.");
        } catch (SQLException e) {
            System.err.println("error al modificar categoría: " + e.getMessage());
        }
    }

    public void eliminarCategoria(int idCategoria) {
        List<Categoria> todos = listarTodas();
        boolean encontrado = false;
        Iterator<Categoria> it = todos.iterator();
        while (it.hasNext()) {
            if (it.next().getIdCategoria() == idCategoria) {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            String sql = "DELETE FROM Categoria WHERE id_categoria = ?";
            try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idCategoria);
                int filas = pstmt.executeUpdate();
                if (filas > 0) // Recorrer por fila de tabla en BD
                    System.out.println("categoría eliminada.");
                else
                    System.out.println("no existe categoría con ese id.");
            } catch (SQLException e) {
                System.err.println("error al eliminar categoría: " + e.getMessage());
            }
        } else {
            System.out.println("no existe categoría con ese id.");
        }
    }

    // LISTA DE CATEGORIAS
    public List<Categoria> listarTodas() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM Categoria";
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre_categoria"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("error al listar categorías: " + e.getMessage());
        }
        return lista;
    }

    // CONSULTA: Buscar por ID
    public Categoria buscarPorId(int idCategoria) {
        String sql = "SELECT * FROM Categoria WHERE id_categoria = ?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCategoria);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre_categoria"));
                return c;
            }
        } catch (SQLException e) {
            System.err.println("error al buscar categoría: " + e.getMessage());
        }
        return null;
    }
}