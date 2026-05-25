import java.util.Date;
import java.util.Scanner;
// Métodos
import DAO.AlquilerD;
import DAO.CategoriaD;
import DAO.ClienteD;
import DAO.VehiculoD;
// Clases
import DTO.Alquiler;
import DTO.Cliente;
import DTO.EstadoContrato;
import DTO.Vehiculo;
import DTO.Categoria;
// Excepciones
import DTO.VehiculoNoDisponibleException;
import DTO.ClienteNoEncontradoException;

public class App {
	public static void main(String[] args) throws VehiculoNoDisponibleException, ClienteNoEncontradoException {
		Scanner sc = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("Gestión de Alquiler de Vehículos");
			System.out.println("1. Gestión de clientes.");
			System.out.println("2. Gestión de vehículos.");
			System.out.println("3. Gestión de alquileres.");
			System.out.println("4. Consultar vehículos disponibles por categoría.");
			System.out.println("5. Consultar historial de alquileres de un cliente.");
			System.out.println("Seleccione una opción: ");
			opcion = sc.nextInt();
			switch (opcion) {
			// GESTION CLIENTES
			case 1:
				System.out.println("-- Gestión de clientes --");
				System.out.println("1. Crear un nuevo cliente.");
				System.out.println("2. Modificar datos de un cliente.");
				System.out.println("3. Eliminar un cliente del sistema.");
				System.out.println("4. Consultar historial de alquileres de un cliente.");
				System.out.println("Seleccione una opción: ");
				opcion = sc.nextInt();
				if (opcion == 1) {
					System.out.println("-- Crear un nuevo cliente --");
					// Método de ClienteD crearCliente
					System.out.println("El nuevo cliente ha sido creado.");
				} else if (opcion == 2) {
					// Modificar datos de un cliente
					System.out.println("-- Modificar datos de un cliente --");
					// Método de ClienteD actualizarCliente
				} else if (opcion == 3) {
					// Eliminar un cliente del sistema
					System.out.println("-- Eliminar un cliente del sistema --");
					System.out.println("Ingrese el DNI del cliente que desea eliminar:");
					String dniEliminar = sc.next();
					System.out.println("El cliente con DNI " + dniEliminar + " ha sido eliminado del sistema.");
				} else if (opcion == 4) {
					// Consultar historial de alquileres de un cliente
					System.out.println("-- Consultar historial de alquileres de un cliente --");
					System.out.println("Ingrese el DNI del cliente para consultar su historial de alquileres:");
					String dniHistorial = sc.next();
					System.out.println("Historial de alquileres del cliente con DNI " + dniHistorial + ":");
					ClienteD.listarClientesYAlquileres();
				}

				// GESTION VEHICULOS
			case 2:
				if (opcion == 1) {
					System.out.println("-- Crear un nuevo vehículo --");
					// Método de VehiculoD crearVehiculo
					System.out.println("El nuevo vehiculo ha sido creado.");
				} else if (opcion == 2) {
					// Modificar datos de un vehículo
					System.out.println("-- Modificar datos de un vehículo --");
					System.out.println("Ingrese la matrícula del vehículo que desea modificar:");
					String matriculaModificar = sc.next();
					System.out.println("Ingrese el nuevo precio por día del vehículo:");
					double nuevoPrecioDia = sc.nextDouble();
					System.out.println("El precio por día del vehículo con matrícula " + matriculaModificar
							+ " ha sido actualizado a: " + nuevoPrecioDia);
				} else if (opcion == 3) {
					// Eliminar un vehículo del sistema
					System.out.println("-- Eliminar un vehículo del sistema --");
					System.out.println("Ingrese la matrícula del vehículo que desea eliminar:");
					String matriculaEliminar = sc.next();
					System.out.println("El vehículo con matrícula " + matriculaEliminar + " ha sido eliminado del sistema.");
				}

				// GESTION ALQUILERES
				System.out.println("-- Gestión de alquileres --");
				System.out.println("1. Crear un nuevo alquiler.");
				System.out.println("2. Modificar datos de un alquiler.");
				System.out.println("3. Eliminar un alquiler del sistema.");
				System.out.println("Seleccione una opción: ");
				opcion = sc.nextInt();
				if (opcion == 1) {
					System.out.println("-- Crear un nuevo alquiler --");
					// Método de AlquilerD crearAlquiler
					System.out.println("El nuevo alquiler ha sido creado.");
				} else if (opcion == 2) {
					// Modificar datos de un alquiler (Se pueden modificar las fechas previstas de
					// devolución y el precio total del alquiler)
					System.out.println("-- Modificar datos de un alquiler --");
					System.out.println("Ingrese el ID del alquiler que desea modificar:");
					int idAlquilerModificar = sc.nextInt();
					System.out.println("Ingrese las nuevas fechas previstas de devolución (formato: dd/MM/yyyy):");
					String nuevasFechasDevolucion = sc.next();
					System.out.println("Ingrese el nuevo precio total del alquiler:");
					double nuevoPrecioTotal = sc.nextDouble();
					System.out.println("El alquiler con ID " + idAlquilerModificar + " ha sido actualizado con las nuevas fechas de devolución: " + nuevasFechasDevolucion+ " y el nuevo precio total: " + nuevoPrecioTotal);
				} else if (opcion == 3) {
					// Eliminar un alquiler del sistema
					System.out.println("-- Eliminar un alquiler del sistema --");
					System.out.println("Ingrese el ID del alquiler que desea eliminar:");
					int idAlquilerEliminar = sc.nextInt();
					System.out.println("El alquiler con ID " + idAlquilerEliminar + " ha sido eliminado del sistema.");
				}
			case 3:
				// Crear un nuevo alquiler (Se asocia el cliente, vehículo, fechas y empleado a
				// cargo)
				System.out.println("-- Crear un nuevo alquiler --");
				AlquilerD.crearAlquiler(null); // Añadir datos del alquiler
				break;
			case 4:
				// Consultar vehículos disponibles por categoría
				System.out.println("-- Consultar vehículos disponibles por categoría --");
				System.out.println("Vehículos disponibles en la categoría:");
				try {
					VehiculoD.listarPorCategoria();
				} catch (Exception e) {
					// Excepcion de VehiculoNoDisponibleException
					System.out.println("No hay vehículos disponibles en la categoría.");
				}
				break;
			case 5:
				// Modificar datos de un vehículo
				System.out.println("-- Modificar datos de un vehículo --");
				System.out.println("Ingrese la matrícula del vehículo que desea modificar:");
				String matriculaModificar = sc.next();
				System.out.println("Ingrese el nuevo precio por día del vehículo:");
				double nuevoPrecioDia = sc.nextDouble();
				System.out.println("El precio por día del vehículo con matrícula " + matriculaModificar
						+ " ha sido actualizado a: " + nuevoPrecioDia);
				break;
			case 6:
				// Eliminar un vehículo del sistema
				System.out.println("-- Eliminar un vehículo del sistema --");
				System.out.println("Ingrese la matrícula del vehículo que desea eliminar:");
				String matriculaEliminar = sc.next();
				System.out
						.println("El vehículo con matrícula " + matriculaEliminar + " ha sido eliminado del sistema.");
				break;
			case 7:
				// Consultar historial de alquileres de un cliente
				System.out.println("-- Consultar historial de alquileres de un cliente --");
				System.out.println("Ingrese el número de licencia de conducir del cliente:");
				String licenciaCliente = sc.next();
				System.out.println("Historial de alquileres del cliente con licencia " + licenciaCliente + ":");
				// Método de ClienteD listarClientesYAlquileres
				try {
					ClienteD.listarClientesYAlquileres();
				} catch (Exception e) {
					// Excepcion de ClienteNoEncontradoException
					System.out.println("No se encontró un cliente con licencia " + licenciaCliente + ".");
				}
				break;
			case 8:
				// Añadir una nueva categoría de vehículos que se asocia el nombre de la
				// categoría del vehículo
				System.out.println("-- Añadir una nueva categoría de vehículos --");
				System.out.println("Ingrese el nombre de la nueva categoría:");
				String nombreCategoria = sc.next();
				CategoriaD.crearCategoria(nombreCategoria);
				break;
			default:
				System.out.println("Opción no válida. Por favor, elija una opción del menú.");
			}
		} while (opcion != 5);
		sc.close();
	}
}