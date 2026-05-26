package DAO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import DTO.Cliente;

public class ClienteD {
	// CRUD COMPLETO 
	public void crearCliente(Cliente cliente) {
		List<Cliente> clientes = new ArrayList<>(); // Lista de clientes
		clientes.add(cliente); // Agregar a la lista
		System.out.println("Cliente creado: " + cliente.getNombre() + " - DNI: " + cliente.getDni() + " - Correo: " + cliente.getCorreo() + " - Teléfono: " + cliente.getTelefono() + " - Carnet de Conducir: " + cliente.getCarnetConducir());
	}
	
	public void modificarCliente(Cliente cliente) { // Se busca por DNI y se actualizan sus datos
		List<Cliente> clientes = new ArrayList<>(); // Lista de clientes
		for (Cliente c : clientes) {
			if (c.getDni().equals(cliente.getDni())) { // Se busca por DNI y actualiza datos
				c.setNombre(cliente.getNombre());   
				c.setCorreo(cliente.getCorreo()); 
				c.setTelefono(cliente.getTelefono());
				c.setCarnetConducir(cliente.getCarnetConducir());
				System.out.println("Cliente modificado.");
				return;
			}
		}
	}
	
	public void eliminarCliente(String dni) { // Iterator para eliminar elementos de la lista mientras se itera
		Iterator<Cliente> iterator = new ArrayList<Cliente>().iterator(); // Lista de clientes
		while (iterator.hasNext()) {
			// Mientras que haya clientes en la lista, se obtiene el siguiente
			Cliente cliente = iterator.next();
			if (cliente.getDni().equals(dni)) { // Se elimina por DNI
				iterator.remove(); // Eliminar el cliente de la lista
				System.out.println("Cliente eliminado.");
				return;
			}
		}
	}
	
    // Consulta BD: Buscar cliente por DNI
    public static Cliente consultarClienteBD(String dni) {
        String sql = "SELECT * FROM clientes WHERE dni = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni); // Sustituye el ? por el DNI
            ResultSet rs = ps.executeQuery(); // Ejecuta la consulta

			if (rs.next()) { // Si existe el cliente
                return new Cliente(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("carnet_conducir")
                );
            }
        } catch (Exception e) {
            System.out.println("Error consultando cliente: " + e.getMessage());
        }
        return null;
    }
}

	// Listar todos los clientes y sus alquileres asociados
  	public static void listarClientesYAlquileres() {
  		ArrayList<Cliente> clientes = new ArrayList<>(); // Lista de clientes
  		for (Cliente cliente : clientes) {
			System.out.println("-----------------------------");
  			System.out.println("Cliente: " + cliente.getNombre() + " - DNI: " + cliente.getDni());
  			System.out.println("Correo: " + cliente.getCorreo() + " - Teléfono: " + cliente.getTelefono() + " - Carnet de Conducir: " + cliente.getCarnetConducir());
  			System.out.println("Alquileres asociados:");
  			// Lista de alquileres asociados al cliente
  			List<String> alquileres = new ArrayList<>();
  			for (String alquiler : alquileres) {
  				System.out.println("  Alquiler: " + alquiler + "Fecha Inicio: " + alquiler + " - Fecha Prevista Devolución: " + alquiler + " - Fecha Real Devolución: " + alquiler + " - Estado: " + alquiler + " - Precio Total: " + alquiler);
  				System.out.println("-----------------------------");
  			}
  		}
	}	
}
