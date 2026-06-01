package DAO;

import DTO.Vehiculo;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import src.ConexionBD;

public class VehiculoD {
    private Connection getConnection() {
        return ConexionBD.conectar();
    }

    // CRUD COMPLETO
    public void crearVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO Vehiculo (matricula, marca, modelo, ano, combustible, plazas, precio_dia, estado, id_categoria) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehiculo.getMatricula());
            pstmt.setString(2, vehiculo.getMarca());
            pstmt.setString(3, vehiculo.getModelo());
            pstmt.setInt(4, vehiculo.getAñoFabricacion());
            pstmt.setString(5, vehiculo.getCombustible());
            pstmt.setInt(6, vehiculo.getPlazas());
            pstmt.setDouble(7, vehiculo.getPrecioDia());
            pstmt.setString(8, vehiculo.getEstado());
            pstmt.setString(9, vehiculo.getCategoria());
            pstmt.executeUpdate();
            System.out.println("Vehículo añadido con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al crear vehículo: " + e.getMessage());
        }
    }

    public void modificarVehiculo(Vehiculo vehiculo) {
        String sql = "UPDATE Vehiculo SET marca=?, modelo=?, ano=?, combustible=?, plazas=?, precio_dia=?, estado=?, id_categoria=? WHERE matricula=?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehiculo.getMarca());
            pstmt.setString(2, vehiculo.getModelo());
            pstmt.setInt(3, vehiculo.getAñoFabricacion());
            pstmt.setString(4, vehiculo.getCombustible());
            pstmt.setInt(5, vehiculo.getPlazas());
            pstmt.setDouble(6, vehiculo.getPrecioDia());
            pstmt.setString(7, vehiculo.getEstado());
            pstmt.setString(8, vehiculo.getCategoria());
            pstmt.setString(9, vehiculo.getMatricula());
            int filas = pstmt.executeUpdate();
            if (filas > 0)
                System.out.println("Vehículo modificado.");
            else
                System.out.println("No se encontró vehículo con esa matrícula.");
        } catch (SQLException e) {
            System.err.println("Error al modificar vehículo: " + e.getMessage());
        }
    }

    public void eliminarVehiculo(String matricula) {
        List<Vehiculo> todos = listarTodos();
        boolean encontrado = false;
        Iterator<Vehiculo> it = todos.iterator();
        while (it.hasNext()) {
            if (it.next().getMatricula().equals(matricula)) {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            String sql = "DELETE FROM Vehiculo WHERE matricula = ?";
            try (Connection conn = getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, matricula);
                int filas = pstmt.executeUpdate();
                if (filas > 0)
                    System.out.println("Vehículo eliminado.");
            } catch (SQLException e) {
                System.err.println("Error al eliminar vehículo: " + e.getMessage());
            }
        } else {
            System.out.println("No existe vehículo con esa matrícula.");
        }
    }

    // LISTAR TODOS
    public List<Vehiculo> listarTodos() {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Vehiculo";
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vehiculo v = new Vehiculo();
                v.setMatricula(rs.getString("matricula"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setAñoFabricacion(rs.getInt("ano"));
                v.setCombustible(rs.getString("combustible"));
                v.setPlazas(rs.getInt("plazas"));
                v.setPrecioDia(rs.getDouble("precio_dia"));
                v.setEstado(rs.getString("estado"));
                v.setCategoria(rs.getString("id_categoria"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar vehículos: " + e.getMessage());
        }
        return lista;
    }

    // LISTAR POR DISPONIBILIDAD
    public List<Vehiculo> listarDisponibles() {
        return listarTodos().stream()
                .filter(v -> "disponible".equalsIgnoreCase(v.getEstado()))
                .collect(Collectors.toList());
    }

    // LISTA DISPONIBLE POR CATEGORIA
    public List<Vehiculo> listarDisponiblesPorCategoria(String idCategoria) {
        return listarTodos().stream()
                .filter(v -> "disponible".equalsIgnoreCase(v.getEstado())) // Busca por "disponible" en la base de datos
                .filter(v -> v.getCategoria().equals(idCategoria)) // Filtra por id al leerlo
                .collect(Collectors.toList()); // Añade a la lista
    }

    // LISTA POR CATEGORIA (con Map)
    public Map<String, List<Vehiculo>> agruparPorCategoria() {
        return listarTodos().stream()
                .collect(Collectors.groupingBy(Vehiculo::getCategoria)); 
    }

    // CONSULTA: Buscar por matrícula
    public Vehiculo buscarPorMatricula(String matricula) {
        return listarTodos().stream()
                .filter(v -> v.getMatricula().equals(matricula))
                .findFirst() 
                .orElse(null);
    }
}
