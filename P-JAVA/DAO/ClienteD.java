package DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DTO.Cliente;

public class ClienteD {
    // Lista en memoria donde se guardan los clientes
    private static List<Cliente> clientes = new ArrayList<>();

    public void crearCliente(Cliente cliente) {
        clientes.add(cliente); // Añade el cliente a la lista
        System.out.println("Cliente creado: " + cliente.getNombre());
    }

    public void modificarCliente(Cliente clienteActualizado) {
        for (Cliente c : clientes) { // Recorre la lista
            if (c.getDni().equals(clienteActualizado.getDni())) { // Busca por DNI
                c.setNombre(clienteActualizado.getNombre());
                c.setCorreo(clienteActualizado.getCorreo());
                c.setTelefono(clienteActualizado.getTelefono());
                c.setCarnetConducir(clienteActualizado.getCarnetConducir());
                System.out.println("Cliente modificado.");
                return;
            }
        }
        System.out.println("Cliente no encontrado.");
    }

    public void eliminarCliente(String dni) {
        Iterator<Cliente> it = clientes.iterator(); // Iterator para eliminar mientras recorres
        while (it.hasNext()) {
            Cliente c = it.next();
            if (c.getDni().equals(dni)) { // Coincidencia por DNI
                it.remove(); // Elimina el cliente
                System.out.println("Cliente eliminado.");
                return;
            }
        }
        System.out.println("Cliente no encontrado.");
    }

    // Buscar cliente por DNI
    public static Cliente buscarPorDNI(String dni) {
        for (Cliente c : clientes) { 
            if (c.getDni().equals(dni)) { // Coincidencia
                return c; 
            }
        }
        return null; // Si no existe
    }

    // Listar todos los clientes
    public static void listarClientes() {
        if (clientes.isEmpty()) { // Si la lista está vacía
            System.out.println("No hay clientes registrados.");
            return;
        }
        for (Cliente c : clientes) { // Imprime cada cliente
            System.out.println(c);
        }
    }
}
