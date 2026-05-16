package DAO;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
// Registrar un nuevo cliente, incluyendo todos sus datos personales y de contacto.
// Dar de alta un nuevo vehículo, indicando matrícula, marca, modelo, año, combustible, plazas, precio por día, estado y categoría.
// Registrar un nuevo alquiler, asociando cliente, vehículo, empleado responsable, fechas e importe total.
// Consultar todos los vehículos disponibles de una categoría específica, por ejemplo turismos, furgonetas o vehículos eléctricos.
// Modificar los datos de un vehículo, como precio por día, estado, combustible o categoría.
// Eliminar un vehículo del sistema si deja de formar parte de la flota.
// Consultar el historial completo de alquileres realizados por un cliente concreto.
// Añadir una nueva categoría de vehículos al catálogo de la empresa.
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenido a Rueda Fácil - Gestión de Alquiler de Vehículos");
		System.out.println("1. Registrar un nuevo cliente");
		System.out.println("2. Dar de alta un nuevo vehículo");
		System.out.println("3. Registrar un nuevo alquiler");
		System.out.println("4. Consultar vehículos disponibles por categoría");
		System.out.println("5. Modificar datos de un vehículo");
		System.out.println("6. Eliminar un vehículo del sistema");
		System.out.println("7. Consultar historial de alquileres de un cliente");
		System.out.println("8. Añadir una nueva categoría de vehículos");
		System.out.println("Seleccione una opción del menú: ");
		int opcion = sc.nextInt();
		
		switch (opcion) {
			case 1:
				// Registrar un nuevo cliente
				break;
			case 2:
				// Dar de alta un nuevo vehículo
				break;
			case 3:
				// Registrar un nuevo alquiler
				break;
			case 4:
				// Consultar vehículos disponibles por categoría
				break;
			case 5:
				// Modificar datos de un vehículo
				break;
			case 6:
				// Eliminar un vehículo del sistema
				break;
			case 7:
				// Consultar historial de alquileres de un cliente
				break;
			case 8:
				// Añadir una nueva categoría de vehículos
				break;
			default:
				System.out.println("Opción no válida. Por favor, elija una opción del menú.");
		}
	}
}
