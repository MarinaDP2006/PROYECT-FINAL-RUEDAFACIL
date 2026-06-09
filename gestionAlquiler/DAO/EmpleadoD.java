package DAO;

import java.sql.*;
import java.util.*;
import DTO.Empleado;
import src.ConexionBD;

public class EmpleadoD {
    private Connection getConnection() {
        return ConexionBD.conectar();
    }

    // CRUD COMPLETO
    public void crearEmpleado(Empleado empleado) {
        String sql = "INSERT INTO Empleado (nombre, cargo, oficina, turno, anos_experiencia) VALUES (?,?,?,?,?)";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getCargo());
            pstmt.setString(3, empleado.getOficina());
            pstmt.setString(4, empleado.getTurno());
            pstmt.setInt(5, empleado.getAñosExp());
            pstmt.executeUpdate();
            System.out.println("Empleado añadido con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al crear empleado: " + e.getMessage());
        }
    }

    public void modificarEmpleado(Empleado empleado) {
        String sql = "UPDATE Empleado SET nombre=?, cargo=?, oficina=?, turno=?, anos_experiencia=? WHERE id_empleado=?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getCargo());
            pstmt.setString(3, empleado.getOficina());
            pstmt.setString(4, empleado.getTurno());
            pstmt.setInt(5, empleado.getAñosExp());
            pstmt.setInt(6, empleado.getIdEmpleado());
            
            // Según las filas de la BD, se actualizan los datos fila por fila (dato x dato)
            int filas = pstmt.executeUpdate();
            if (filas > 0) {// Se ejecuta UPDATE en la BD  y se modifican lls datos
                System.out.println("Empleado modificado.");
            } else { // No se encuentra el ID del empleado pq es incorrecto
                System.out.println("No se encontró empleado con ese id.");
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar empleado: " + e.getMessage());
        }
    }

    public void eliminarEmpleado(int idEmpleado) {
        List<Empleado> todos = listarTodos();
        boolean encontrado = false;
        Iterator<Empleado> it = todos.iterator();
        while (it.hasNext()) {
            if (it.next().getIdEmpleado() == idEmpleado) {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            String sql = "DELETE FROM Empleado WHERE id_empleado = ?";
            try (Connection conn = getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idEmpleado);
                // Según las filas de la BD, se actualizan los datos fila por fila (dato x dato).
                int filas = pstmt.executeUpdate(); // Se ejecuta UPDATE en la BD  y se modifican los datos
                if (filas > 0) 
                    System.out.println("Empleado eliminado.");
                else
                    System.out.println("No existe empleado con ese id.");
            } catch (SQLException e) {
                System.err.println("Error al eliminar empleado: " + e.getMessage());
            }
        } else {
            System.out.println("No existe empleado con ese id.");
        }
    }

    // LISTA DE EMPLEADOS
    public List<Empleado> listarTodos() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM Empleado";
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setIdEmpleado(rs.getInt("id_empleado"));
                e.setNombre(rs.getString("nombre"));
                e.setCargo(rs.getString("cargo"));
                e.setOficina(rs.getString("oficina"));
                e.setTurno(rs.getString("turno"));
                e.setAñosExp(rs.getInt("anos_experiencia"));
                lista.add(e);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar empleados: " + e.getMessage());
        }
        return lista;
    }

    // CONSULTA: Buscar por ID
    public Empleado buscarPorId(int idEmpleado) {
        String sql = "SELECT * FROM Empleado WHERE id_empleado = ?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idEmpleado);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Empleado e = new Empleado();
                e.setIdEmpleado(rs.getInt("id_empleado"));
                e.setNombre(rs.getString("nombre"));
                e.setCargo(rs.getString("cargo"));
                e.setOficina(rs.getString("oficina"));
                e.setTurno(rs.getString("turno"));
                e.setAñosExp(rs.getInt("anos_experiencia"));
                return e;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar empleado: " + e.getMessage());
        }
        return null;
    }
}