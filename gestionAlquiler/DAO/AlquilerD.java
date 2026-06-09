package DAO;

import DTO.Alquiler;
import DTO.ClienteNoEncontradoException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import src.ConexionBD;

public class AlquilerD {
    private Connection getConnection() {
        return ConexionBD.conectar();
    }

    // CRUD COMPLETO
    public void crearAlquiler(Alquiler alquiler) throws ClienteNoEncontradoException {
        // Verificar que el cliente existe
        ClienteD clienteDAO = new ClienteD();
        if (clienteDAO.buscarPorDNI(alquiler.getIdCliente()) == null) { throw new ClienteNoEncontradoException("No existe cliente con DNI: " + alquiler.getIdCliente());
        }
        // Insertar nuevos datos
        String sql = "INSERT INTO Alquiler (fecha_inicio, fecha_devolucion_prevista, id_cliente, id_empleado, matricula, precio_total, estado_contrato) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(alquiler.getFechaInicio()));
            pstmt.setDate(2, Date.valueOf(alquiler.getFechaPrevistaDevolucion()));
            pstmt.setString(3, alquiler.getIdCliente());
            pstmt.setInt(4, alquiler.getIdEmpleado());
            pstmt.setString(5, alquiler.getMatricula());
            pstmt.setDouble(6, alquiler.getPrecioTotal());
            pstmt.setString(7, alquiler.getEstadoContrato());
            pstmt.executeUpdate();
            System.out.println("Alquiler creado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al crear alquiler: " + e.getMessage());
        }
    }

    public void eliminarAlquiler(int idAlquiler) {
        List<Alquiler> todos = listarTodos();
        boolean encontrado = false;
        Iterator<Alquiler> it = todos.iterator();
        while (it.hasNext()) {
            if (it.next().getIdAlquiler() == idAlquiler) {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            String sql = "DELETE FROM Alquiler WHERE id_alquiler = ?";
            try (Connection conn = getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idAlquiler);
                int filas = pstmt.executeUpdate();
                if (filas > 0) { // Se ejecuta UPDATE en la BD  y se modifican los datos
                    System.out.println("Alquiler eliminado.");
                } else {
                    System.out.println("No existe alquiler con ese ID.");
                }
            } catch (SQLException e) {
                System.err.println("Error al eliminar alquiler: " + e.getMessage());
            }
        } else {
            System.out.println("No existe alquiler con ese ID.");
        }
    }

    // LISTA DE ALQUILER
    public List<Alquiler> listarTodos() {
        List<Alquiler> lista = new ArrayList<>();
        String sql = "SELECT * FROM Alquiler";
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
        	// Mientras haya Alquiler, muestra todos los datos
            while (rs.next()) {
                Alquiler a = new Alquiler();
                a.setIdAlquiler(rs.getInt("id_alquiler"));
                a.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                a.setFechaPrevistaDevolucion(rs.getDate("fecha_devolucion_prevista").toLocalDate());
                Date real = rs.getDate("fecha_devolucion_real");
                // Muestra la fecha y las demás filas
                if (real != null)
                a.setFechaRealDevolucion(real.toLocalDate());
                a.setIdCliente(rs.getString("id_cliente"));
                a.setIdEmpleado(rs.getInt("id_empleado"));
                a.setMatricula(rs.getString("matricula"));
                a.setPrecioTotal(rs.getDouble("precio_total"));
                String estadoStr = rs.getString("estado_contrato");
                if (estadoStr != null) {
                    a.setEstadoContrato(estadoStr);
                }
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar alquileres: " + e.getMessage());
        }
        return lista;
    }

    // LISTA POR ALQUILER ACTIVO
    public List<Alquiler> listarActivos() {
        return listarTodos().stream() 
        		.filter(a -> a.getEstadoContrato().equalsIgnoreCase("activo"))
                .collect(Collectors.toList());
    }

    // LISTA AGRUPANDO POR CLIENTE (Map)
    public Map<String, List<Alquiler>> agruparPorCliente() {
        return listarTodos().stream()
                .collect(Collectors.groupingBy(Alquiler::getIdCliente));     
        }

    // CONSULTA: Buscar por DNI
    public List<Alquiler> consultarPorCliente(String dni) {
        return listarTodos().stream()
                .filter(a -> a.getIdCliente().equals(dni)) 
                .collect(Collectors.toList()); 
    }
}