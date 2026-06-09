package src;

import DAO.AlquilerD;
import DAO.ClienteD;
import DAO.EmpleadoD;
import DAO.VehiculoD;
import DTO.Alquiler;
import DTO.Cliente;
import DTO.ClienteNoEncontradoException;
import DTO.Empleado;
import DTO.Vehiculo;
import DTO.VehiculoNoDisponibleException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App_Main {
	public static void main(String[] args) throws ClienteNoEncontradoException, VehiculoNoDisponibleException {
		Scanner sc = new Scanner(System.in);
		// Conectar con la base de datos al iniciar la aplicación
		try {
			ConexionBD.conectar();
		} catch (Exception e) {
			System.out.println("Error al conectar con la base de datos: " + e.getMessage());
			return;
		}

		int opcion;
		do {
			System.out.println();
			System.out.println("--- MENÚ PRINCIPAL_ALQUILERES --- ");
			System.out.println("1. Gestión Clientes");
			System.out.println("2. Gestión Vehículos");
			System.out.println("3. Gestión Alquileres");
			System.out.println("4. Gestión Empleados");
			System.out.println("5. Historial de alquileres de un cliente");
			System.out.println("0. Salir");
			System.out.print("Opción: ");
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				System.out.println();
				menuClientes();
				break;
			case 2:
				System.out.println();
				menuVehiculos();
				break;
			case 3:
				System.out.println();
				menuAlquileres();
				break;
			case 4:
				System.out.println();
				menuEmpleados();
				break;
			case 5:
				System.out.println();
				historialCliente();
				break;
			case 0:
				System.out.println("Saliendo del sistema...");
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (opcion != 0);
	}

	private static void menuClientes() {
		Scanner sc = new Scanner(System.in);
		int op;
		ClienteD clienteDAO = new ClienteD();
		do {
			System.out.println("\n--- GESTIÓN CLIENTES ---");
			System.out.println("1. Añadir cliente");
			System.out.println("2. Modificar cliente");
			System.out.println("3. Eliminar cliente");
			System.out.println("4. Buscar cliente por DNI");
			System.out.println("5. Listar todos los clientes");
			System.out.println("0. Volver");
			System.out.print("Opción: ");
			op = sc.nextInt();
			sc.nextLine();

			switch (op) {
			case 1:
				Cliente c = new Cliente();
				System.out.println();
				System.out.print("DNI: ");
				c.setDni(sc.nextLine());
				System.out.print("Nombre: ");
				c.setNombre(sc.nextLine());
				System.out.print("Teléfono: ");
				c.setTelefono(sc.nextLine());
				System.out.print("Email: ");
				c.setCorreo(sc.nextLine());
				System.out.println("Direccion: ");
				c.setDireccion(sc.nextLine());
				System.out.print("Carnet: ");
				c.setCarnetConducir(sc.nextLine());
				clienteDAO.crearCliente(c);
				break;
			case 2:
				System.out.println();
				System.out.print("DNI a modificar: ");
				String dniMod = sc.nextLine();
				Cliente existente = clienteDAO.buscarPorDNI(dniMod);
				if (existente != null) {
					System.out.print("Nuevo nombre (" + existente.getNombre() + "): ");
					existente.setNombre(sc.nextLine());
					System.out.print("Nuevo teléfono: ");
					existente.setTelefono(sc.nextLine());
					System.out.print("Nuevo email: ");
					existente.setCorreo(sc.nextLine());
					System.out.println("Nueva direccion: ");
					existente.setDireccion(sc.nextLine());
					System.out.print("Nuevo carnet: ");
					existente.setCarnetConducir(sc.nextLine());
					clienteDAO.modificarCliente(existente);
				} else
					System.out.println("Cliente no encontrado");
				break;
			case 3:
				System.out.println();
				System.out.print("DNI a eliminar: ");
				clienteDAO.eliminarCliente(sc.nextLine());
				break;
			case 4:
				System.out.print("DNI a buscar: ");
				Cliente encontrado = clienteDAO.buscarPorDNI(sc.nextLine());
				System.out.println(encontrado != null ? "Cliente encontrado: " + encontrado.getNombre(): "No existe cliente con ese DNI.");
				break;
			case 5:
				System.out.println();
				clienteDAO.listarClientesPorNombre().forEach(System.out::println);
				break;
			case 0:
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (op != 0);
	}

	private static void menuVehiculos() throws VehiculoNoDisponibleException {
		Scanner sc = new Scanner(System.in);
		int op;
		VehiculoD vehiculoDAO = new VehiculoD();
		do {
			System.out.println("\n--- GESTIÓN VEHÍCULOS ---");
			System.out.println("1. Añadir vehículo");
			System.out.println("2. Buscar por matrícula");
			System.out.println("3. Modificar vehículo");
			System.out.println("4. Eliminar vehículo");
			System.out.println("5. Listar todos los vehículos");
			System.out.println("6. Vehículos disponibles por categoría");
			System.out.println("0. Volver");
			System.out.print("Opción: ");
			op = sc.nextInt();
			sc.nextLine();

			switch (op) {
			case 1:
				Vehiculo v = new Vehiculo();
				System.out.println();
				System.out.print("Matrícula (Empieza con V00): ");
				v.setMatricula(sc.nextLine());
				System.out.print("Marca: ");
				v.setMarca(sc.nextLine());
				System.out.print("Modelo: ");
				v.setModelo(sc.nextLine());
				System.out.print("Año: ");
				int anio = sc.nextInt();
				v.setAñoFabricacion(anio);
				sc.nextLine();
				System.out.print("Combustible (Diesel, Gasolina,Electrico): ");
				v.setCombustible(sc.nextLine());
				System.out.print("Plazas: ");
				v.setPlazas(sc.nextInt());
				sc.nextLine();
				System.out.print("Precio día: ");
				v.setPrecioDia(sc.nextDouble());
				sc.nextLine();
				System.out.print("Estado (Disponible/No disponible): ");
				v.setEstado(sc.nextLine());
				System.out.print("ID categoría (1-6): ");
				v.setCategoria(sc.nextLine());
				vehiculoDAO.crearVehiculo(v);
				break;
			case 2:
				System.out.println();
				System.out.print("Matrícula a buscar: ");
				String mat = sc.nextLine();
				// Se busca el vehiculo por la matricula y se muestran los datos
				Vehiculo encontradoV = vehiculoDAO.buscarPorMatricula(mat);
				System.out.println(encontradoV != null ? "Matrícula: " + encontradoV.getMatricula() + ", Marca: " + encontradoV.getMarca()
														+ ", Modelo: " + encontradoV.getModelo(): "No encontrado");
				break;
			case 3:
				System.out.println();
				System.out.print("Matrícula a modificar: ");
				// Se busca el vehiculo por la matricula y se muestran los datos a modificar
				String matMod = sc.nextLine();
				Vehiculo vMod = vehiculoDAO.buscarPorMatricula(matMod);
				if (vMod != null) {
					System.out.print("Nueva marca (" + vMod.getMarca() + "): ");
					vMod.setMarca(sc.nextLine());
					System.out.print("Nuevo modelo: ");
					vMod.setModelo(sc.nextLine());
					System.out.print("Nuevo año: ");
					vMod.setAñoFabricacion(sc.nextInt());
					sc.nextLine();
					System.out.print("Nuevo combustible: ");
					vMod.setCombustible(sc.nextLine());
					System.out.print("Nuevas plazas: ");
					vMod.setPlazas(sc.nextInt());
					sc.nextLine();
					System.out.print("Nuevo precio día: ");
					vMod.setPrecioDia(sc.nextDouble());
					sc.nextLine();
					System.out.print("Nuevo estado: ");
					vMod.setEstado(sc.nextLine());
					System.out.print("Nuevo ID categoría: ");
					vMod.setCategoria(sc.nextLine());
					vehiculoDAO.modificarVehiculo(vMod);
				} else
					System.out.println("No encontrado");
				break;
			case 4:
				System.out.print("Matrícula a eliminar: ");
				vehiculoDAO.eliminarVehiculo(sc.nextLine());
				break;
			case 5:
				vehiculoDAO.listarTodos().forEach(System.out::println);
				break;
			case 6:
				System.out.print("ID de la categoría: ");
				String idCat = sc.next();
				sc.nextLine();
				// Muestra solo los vehículos que están disponibles y pertenecen a una categoría
				List<Vehiculo> disponibles = vehiculoDAO.listarDisponiblesPorCategoria(idCat);
				if (disponibles.isEmpty())
					System.out.println("No hay vehículos disponibles en esa categoría.");
				else
					disponibles.forEach(vv -> System.out.println(vv.getMatricula() + " - " + vv.getModelo()));
				break;
			case 0:
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (op != 0);
	}

	private static void menuAlquileres() throws ClienteNoEncontradoException {
		Scanner sc = new Scanner(System.in);
		int op;
		AlquilerD alquilerDAO = new AlquilerD();
		do {
			System.out.println("\n--- GESTIÓN ALQUILERES ---");
			System.out.println("1. Crear alquiler");
			System.out.println("2. Eliminar alquiler");
			System.out.println("3. Listar todos");
			System.out.println("4. Listar activos");
			System.out.println("5. Agrupar por cliente");
			System.out.println("0. Volver");
			System.out.print("Opción: ");
			op = sc.nextInt();
			sc.nextLine();

			switch (op) {
			case 1:
				Alquiler a = new Alquiler();
				System.out.print("Fecha inicio (YYYY-MM-DD): ");
				a.setFechaInicio(LocalDate.parse(sc.nextLine()));
				System.out.print("Fecha prevista devolución (YYYY-MM-DD): ");
				a.setFechaPrevistaDevolucion(LocalDate.parse(sc.nextLine()));
				System.out.print("DNI cliente: ");
				a.setIdCliente(sc.nextLine());
				System.out.print("ID empleado: ");
				a.setIdEmpleado(sc.nextInt());
				sc.nextLine();
				System.out.println("Matrícula del vehiculo: ");
				a.setMatricula(sc.nextLine());
				System.out.print("Precio: ");
				a.setPrecioTotal(sc.nextInt());
				sc.nextLine();
				System.out.print("Estado (Activo/Completado/Cancelado): ");
				String estado = sc.nextLine();
				a.setEstadoContrato(estado);
				alquilerDAO.crearAlquiler(a);
				break;
			case 2:
				System.out.print("ID alquiler a eliminar: ");
				alquilerDAO.eliminarAlquiler(sc.nextInt());
				break;
			case 3:
				// Muestra todos los alquileres registrados, sin filtros
				alquilerDAO.listarTodos().forEach(System.out::println);
				break;
			case 4:
				// Muestra solo los alquileres con el estado ACTIVO
				alquilerDAO.listarActivos().forEach(System.out::println);
				break;
			case 5:
				// Agrupa todos los alquileres por DNI del cliente y muestra cuántos tiene cada uno
				Map<String, List<Alquiler>> grupo = alquilerDAO.agruparPorCliente();
				grupo.forEach((dni, lista) -> System.out.println("Cliente " + dni + ": " + lista.size() /*cuenta cuantos tiene*/ + " alquileres."));
				break;
			case 0:
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (op != 0);
	}

	private static void menuEmpleados() {
		Scanner sc = new Scanner(System.in);
		int op;
		EmpleadoD empleadoDAO = new EmpleadoD();
		do {
			System.out.println("\n--- GESTIÓN EMPLEADOS ---");
			System.out.println("1. Añadir empleado");
			System.out.println("2. Modificar empleado");
			System.out.println("3. Eliminar empleado");
			System.out.println("4. Listar todos");
			System.out.println("0. Volver");
			System.out.print("Opción: ");
			op = sc.nextInt();
			sc.nextLine();

			switch (op) {
			case 1:
				Empleado e = new Empleado();
				System.out.print("Nombre: ");
				e.setNombre(sc.nextLine());
				System.out.print("Cargo (AtencionCliente/PreparadorVehiculo/Mecanico/Inspector/Director): ");
				e.setCargo(sc.nextLine());
				System.out.print("Oficina (A, B o C): ");
				e.setOficina(sc.nextLine());
				System.out.print("Turno (mañana/tarde): ");
				e.setTurno(sc.nextLine());
				System.out.print("Años experiencia: ");
				int exp = sc.nextInt();
				e.setAñosExp(exp);
				sc.nextLine();
				empleadoDAO.crearEmpleado(e);
				break;
			case 2:
				System.out.print("ID empleado: ");
				int id = sc.nextInt();
				sc.nextLine();
				Empleado emp = empleadoDAO.buscarPorId(id);
				if (emp != null) {
					System.out.print("Nuevo nombre (" + emp.getNombre() + "): ");
					emp.setNombre(sc.nextLine());
					System.out.print("Nuevo cargo: ");
					emp.setCargo(sc.nextLine());
					System.out.print("Nueva oficina: ");
					emp.setOficina(sc.nextLine());
					System.out.print("Nuevo turno: ");
					emp.setTurno(sc.nextLine());
					System.out.print("Nuevos años experiencia: ");
					int nuevaExp = sc.nextInt();
					emp.setAñosExp(nuevaExp);
					sc.nextLine();
					empleadoDAO.modificarEmpleado(emp);
				} else
					System.out.println("No encontrado");
				break;
			case 3:
				System.out.print("ID a eliminar: ");
				empleadoDAO.eliminarEmpleado(sc.nextInt());
				break;
			case 4:
				empleadoDAO.listarTodos().forEach(System.out::println);
				break;
			case 0:
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (op != 0);
	}

	private static void historialCliente() {
		Scanner sc = new Scanner(System.in);
		System.out.print("DNI del cliente: ");
		String dni = sc.nextLine();

		// Muestra todos los alquileres que ha hecho un cliente
		AlquilerD alquilerDAO = new AlquilerD();
		List<Alquiler> lista = alquilerDAO.consultarPorCliente(dni);
		if (lista.isEmpty()) {
			System.out.println("El cliente no tiene alquileres.");
		} else {
			lista.forEach(a -> System.out.println("ID: " + a.getIdAlquiler() + "| Inicio: " + a.getFechaInicio()+ "| Estado: " + a.getEstadoContrato()));
		}
	}
}