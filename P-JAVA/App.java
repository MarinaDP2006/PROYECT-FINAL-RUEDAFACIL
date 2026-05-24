import java.util.Date;
import java.util.Scanner;

// Métodos
import DAO.ClienteD;
import DAO.VehiculoD;

// Clases
import DTO.Alquiler;
import DTO.Cliente;
import DTO.EstadoContrato;
import DTO.Vehiculo;

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
					System.out.println("Ingrese el nombre del cliente:");
				    String nombreCliente = sc.next();
				    System.out.println("Ingrese el DNI del cliente:");
				    String dniCliente = sc.next();
				    System.out.println("Ingrese el correo electrónico del cliente:");
				    String correoCliente = sc.next();
				    System.out.println("Ingrese el número de teléfono del cliente:");
				    String telefonoCliente = sc.next();
				    System.out.println("Ingrese el número de carnet de conducir del cliente:");
				    String carnetConducirCliente = sc.next();
				    Cliente nuevoCliente = new Cliente(nombreCliente, dniCliente, correoCliente, telefonoCliente, carnetConducirCliente);
				    System.out.println("El nuevo cliente ha sido creado.");
				} else if (opcion == 2) {
				// Modificar datos de un cliente
			   System.out.println("-- Modificar datos de un cliente --");
			    System.out.println("Ingrese el DNI del cliente que desea modificar:");
			    String dniModificar = sc.next();
			    System.out.println("Ingrese el nuevo nombre del cliente:");
			    String nuevoNombre = sc.next();
			    System.out.println("Ingrese el nuevo correo electrónico del cliente:");
			    String nuevoCorreo = sc.next();
			    System.out.println("Ingrese el nuevo número de teléfono del cliente:");
			    String nuevoTelefono = sc.next();
			    System.out.println("Ingrese el nuevo número de carnet de conducir del cliente:");
			    String nuevoCarnetConducir = sc.next();
			    System.out.println("El cliente con DNI " + dniModificar + " ha sido modificado con los siguientes datos: Nombre: " + nuevoNombre + ", Correo: " + nuevoCorreo + ", Teléfono: " + nuevoTelefono + ", Carnet de Conducir: " + nuevoCarnetConducir);
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
					System.out.println("Ingrese la matrícula del vehículo:");
				    String matriculaVehiculo = sc.next();
				    System.out.println("Ingrese la marca del vehículo:");
				    String marcaVehiculo = sc.next();
				    System.out.println("Ingrese el modelo del vehículo:");
				    String modeloVehiculo = sc.next();
				    System.out.println("Ingrese el año de fabricación del vehículo:");
				    int anioFabricacion = sc.nextInt();
				    System.out.println("Ingrese el precio por día del vehículo:");
				    double precioDia = sc.nextDouble();
				    System.out.println("Ingrese el estado del vehículo (disponible, alquilado, en mantenimiento):");
				    String estado = sc.next();
				    System.out.println("Ingrese la categoría del vehículo:");
				    String categoriaVehiculo = sc.next();
				    Vehiculo nuevoVehiculo = new Vehiculo(matriculaVehiculo, marcaVehiculo, modeloVehiculo, anioFabricacion, categoriaVehiculo, anioFabricacion, precioDia, estado, categoriaVehiculo);
				    System.out.println("El nuevo vehiculo ha sido creado.");
				} else if (opcion == 2) {
					// Modificar datos de un vehículo
					System.out.println("-- Modificar datos de un vehículo --");
				    System.out.println("Ingrese la matrícula del vehículo que desea modificar:");
				    String matriculaModificar = sc.next();
				    System.out.println("Ingrese el nuevo precio por día del vehículo:");
				    double nuevoPrecioDia = sc.nextDouble();
				    System.out.println("El precio por día del vehículo con matrícula " + matriculaModificar + " ha sido actualizado a: " + nuevoPrecioDia);
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
				if(opcion == 1) {
					System.out.println("-- Crear un nuevo alquiler --");
					System.out.println("Ingrese el ID del alquiler:");
				    int idAlquiler = sc.nextInt();
					System.out.println("Ingrese el ID del cliente que realizará el alquiler:");
				    int idCliente = sc.nextInt();
				    System.out.println("Ingrese el ID del empleado a cargo del alquiler:");
				    int idEmpleado = sc.nextInt();
				    System.out.println("Ingrese la fecha de inicio del alquiler (formato: dd/MM/yyyy):");
				    Date fechaInicio = sc.next();
				    System.out.println("Ingrese el estado del contrato (activo, finalizado, cancelado):");
				    EstadoContrato estadoContrato = EstadoContrato.valueOf(sc.next().toUpperCase());
				    System.out.println("Ingrese el precio total del alquiler:");
				    double precioTotal = sc.nextDouble();
				    Alquiler nuevoAlquiler = new Alquiler();
				    System.out.println("El nuevo alquiler ha sido creado.");
				} else if (opcion == 2) {
					// Modificar datos de un alquiler (Se pueden modificar las fechas previstas de devolución y el precio total del alquiler)
					System.out.println("-- Modificar datos de un alquiler --");
				    System.out.println("Ingrese el ID del alquiler que desea modificar:");
				    int idAlquilerModificar = sc.nextInt();
				    System.out.println("Ingrese la nueva fecha prevista de devolución (formato: dd/MM/yyyy):");
				    String nuevaFechaPrevista = sc.next();
				    System.out.println("Ingrese el nuevo precio total del alquiler:");
				    double nuevoPrecioTotal = sc.nextDouble();
				    System.out.println("El alquiler con ID " + idAlquilerModificar + " ha sido modificado con los siguientes datos: Nueva Fecha Prevista Devolución: " + nuevaFechaPrevista + ", Nuevo Precio Total: " + nuevoPrecioTotal);
				} else if (opcion == 3) {
					// Eliminar un alquiler del sistema
				    System.out.println("-- Eliminar un alquiler del sistema --");
				    System.out.println("Ingrese el ID del alquiler que desea eliminar:");
				    int idAlquilerEliminar = sc.nextInt();
				    System.out.println("El alquiler con ID " + idAlquilerEliminar + " ha sido eliminado del sistema.");
				}
			case 3:
				// Crear un nuevo alquiler (Se asocia el cliente, vehículo, fechas y empleado a cargo)
				System.out.println("-- Crear un nuevo alquiler --");
				AlquilerD.crearAlquiler();
			    break;			    
			case 4:
				// Consultar vehículos disponibles por categoría
				System.out.println("-- Consultar vehículos disponibles por categoría --");
			    System.out.println("Ingrese la categoría de vehículos que desea consultar:");
			    String categoria = sc.next();
			    System.out.println("Vehículos disponibles en la categoría " + categoria + ":");
			    try {
				    VehiculoD.listarPorCategoria(categoria);
				} catch (Exception e) {
					// Excepcion de VehiculoNoDisponibleException
					System.out.println("No hay vehículos disponibles en la categoría " + categoria + ".");					
				}
			    break;
			case 5:
				// Modificar datos de un vehículo
				System.out.println("-- Modificar datos de un vehículo --");
			    System.out.println("Ingrese la matrícula del vehículo que desea modificar:");
			    String matriculaModificar = sc.next();
			    System.out.println("Ingrese el nuevo precio por día del vehículo:");
			    double nuevoPrecioDia = sc.nextDouble();
			    System.out.println("El precio por día del vehículo con matrícula " + matriculaModificar + " ha sido actualizado a: " + nuevoPrecioDia);
				break;
			case 6:
				// Eliminar un vehículo del sistema
				System.out.println("-- Eliminar un vehículo del sistema --");
			    System.out.println("Ingrese la matrícula del vehículo que desea eliminar:");
			    String matriculaEliminar = sc.next();
			    System.out.println("El vehículo con matrícula " + matriculaEliminar + " ha sido eliminado del sistema.");
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
				// Añadir una nueva categoría de vehículos que se asocia el nombre de la categoría del vehículo
				System.out.println("-- Añadir una nueva categoría de vehículos --");
			    System.out.println("Ingrese el nombre de la nueva categoría:");
			    String nuevaCategoria = sc.next();
			    System.out.println("La categoría " + nuevaCategoria + " ha sido añadida al sistema.");
				break;
			default:
				System.out.println("Opción no válida. Por favor, elija una opción del menú.");
		}
	} while (opcion != 5);
		sc.close();
	}
}
