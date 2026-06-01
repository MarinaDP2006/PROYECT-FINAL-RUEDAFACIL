package DAO;

import DTO.Cliente;
import java.sql.*;
import java.util.*;
import src.ConexionBD;

public class ClienteD {
    private Connection getConnection() {
        return ConexionBD.conectar();
    }

    // CRUD COMPLETO
    public void crearCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (dni, nombre, telefono, email, num_carnet) VALUES (?,?,?,?,?)";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cliente.getDni());
                pstmt.setString(2, cliente.getNombre());
                pstmt.setString(3, cliente.getTelefono());
                pstmt.setString(4, cliente.getCorreo());
                pstmt.setString(5, cliente.getCarnetConducir());
                pstmt.executeUpdate();
             System.out.println("Cliente añadido con éxito a la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al crear cliente: " + e.getMessage());
        }
    }

    public void modificarCliente(Cliente clienteActualizado) {
        String sql = "UPDATE Cliente SET nombre=?, telefono=?, email=?, num_carnet=? WHERE dni=?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, clienteActualizado.getNombre());
                pstmt.setString(2, clienteActualizado.getTelefono());
                   pstmt.setString(3, clienteActualizado.getCorreo());
                pstmt.setString(4, clienteActualizado.getCarnetConducir());
                pstmt.setString(5, clienteActualizado.getDni());
            int filas = pstmt.executeUpdate(); // Actualiza las filas de la tabla cliente
            if (filas > 0)
                System.out.println("Cliente modificado correctamente.");
            else
                System.out.println("No se encontró cliente con ese DNI.");
        } catch (SQLException e) {
            System.err.println("Error al modificar cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(String dni) {
        if (buscarPorDNI(dni) == null) {
            System.out.println("No existe cliente con ese DNI.");
            return;
        }
        String sql = "DELETE FROM Cliente WHERE dni = ?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            int filas = pstmt.executeUpdate();
            if (filas > 0)
                System.out.println("Cliente eliminado.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
        }
    }

    // LISTA DE CLIENTES POR NOMBRE (ordenada)
    public List<Cliente> listarClientesPorNombre() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente ORDER BY nombre";
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setTelefono(rs.getString("telefono"));
                c.setCorreo(rs.getString("email"));
                c.setCarnetConducir(rs.getString("num_carnet"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    // CONSULTA: Buscar por DNI
    public Cliente buscarPorDNI(String dni) {
        String sql = "SELECT * FROM Cliente WHERE dni = ?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Cliente c = new Cliente();
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setTelefono(rs.getString("telefono"));
                c.setCorreo(rs.getString("email"));
                c.setCarnetConducir(rs.getString("num_carnet"));
                return c;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }
}
