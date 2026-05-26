import java.util.Scanner;
import DAO.AlquilerD;
import DAO.CategoriaD;
import DAO.ClienteD;
import DAO.VehiculoD;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- RUEDA FÁCIL ---");
            System.out.println("1. Gestión de clientes");
            System.out.println("2. Gestión de vehículos");
            System.out.println("3. Gestión de alquileres");
            System.out.println("4. Vehículos disponibles por categoría");
            System.out.println("5. Historial de alquileres de un cliente");
            System.out.println("6. Listar categorías");
            System.out.println("7. Listar empleados");
            System.out.println("0. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
			case 1: 
                System.out.println("\n--- Gestión de clientes ---");
                System.out.println("1. Crear cliente");
                System.out.println("2. Modificar cliente");
                System.out.println("3. Eliminar cliente");
                System.out.println("4. Buscar cliente por DNI");
                System.out.println("5. Listar clientes");
                int opcCliente = sc.nextInt();
                ClienteD clienteDAO = new ClienteD();

                switch (opcCliente) {
                case 1: // Crear cliente
                    System.out.println("DNI:");
                    String dni = sc.next();
                    System.out.println("Nombre:");
                    String nombre = sc.next();
                    System.out.println("Correo:");
                    String correo = sc.next();
                    System.out.println("Teléfono:");
                    String tel = sc.next();
                    System.out.println("Carnet conducir:");
                    String carnet = sc.next();
                    Cliente nuevo = new Cliente(dni, nombre, correo, tel, carnet);
                    clienteDAO.crearCliente(nuevo); // Añade a la lista
                    break;
				case 2: // Modificar cliente
                    System.out.println("DNI del cliente a modificar:");
                    String dniMod = sc.next();
                    Cliente mod = new Cliente(dniMod, "NuevoNombre", "NuevoCorreo", "NuevoTel", "NuevoCarnet");
                    clienteDAO.modificarCliente(mod);
                    break;
                case 3: // Eliminar cliente
                    System.out.println("DNI del cliente a eliminar:");
                    String dniDel = sc.next();
                    clienteDAO.eliminarCliente(dniDel);
                    break;
                case 4: // Buscar cliente
                    System.out.println("DNI a buscar:");
                    String dniBus = sc.next();
                    Cliente encontrado = ClienteD.buscarPorDNI(dniBus);
                    System.out.println(encontrado != null ? encontrado : "No encontrado");
                    break;
                case 5: // Listar clientes
                    ClienteD.listarClientes();
                    break;
                }
                break;
			case 2:
                System.out.println("\n--- Gestión de vehículos ---");
                System.out.println("1. Crear vehículo");
                System.out.println("2. Modificar vehículo");
                System.out.println("3. Eliminar vehículo");
                System.out.println("4. Buscar por matrícula");
                System.out.println("5. Listar vehículos");
                int opcVeh = sc.nextInt();
                VehiculoD vehDAO = new VehiculoD();
                switch (opcVeh) {
                case 1: // Crear vehículo
                    System.out.println("Matrícula:");
                    String mat = sc.next();
                    System.out.println("Modelo:");
                    String modVeh = sc.next();
                    System.out.println("Precio/día:");
                    double precio = sc.nextDouble();
                    Categoria cat = new Categoria(1, "General"); // Categoría de ejemplo
                    Vehiculo nuevoVeh = new Vehiculo(mat, modVeh, precio, true, cat);
                    vehDAO.crearVehiculo(nuevoVeh);
                    break;
                case 2: // Modificar vehículo
                    System.out.println("Matrícula del vehículo a modificar:");
                    String matMod = sc.next();
                    Vehiculo vehMod = new Vehiculo(matMod, "NuevoModelo", 99.99, true, new Categoria(1, "General"));
                    vehDAO.actualizarVehiculo(vehMod);
                    break;
                case 3: // Eliminar vehículo
                    System.out.println("Matrícula del vehículo a eliminar:");
                    String matDel = sc.next();
                    Vehiculo vehDel = new Vehiculo(matDel, "", 0, true, null);
                    vehDAO.eliminarVehiculo(vehDel);
                    break;
                case 4: // Buscar vehículo
                    System.out.println("Matrícula a buscar:");
                    String matBus = sc.next();
                    Vehiculo v = VehiculoD.buscarPorMatricula(matBus);
                    System.out.println(v != null ? v : "No encontrado");
                    break;
                case 5: // Listar vehículos
                    VehiculoD.listarTodos();
                    break;
                }
                break;			
				case 3: 
                System.out.println("\n--- Gestión de alquileres ---");
                System.out.println("1. Crear alquiler");
                System.out.println("2. Modificar alquiler");
                System.out.println("3. Eliminar alquiler");
                System.out.println("4. Listar alquileres");
                int opcAlq = sc.nextInt();
                switch (opcAlq) {
                case 1: // Crear alquiler
                    System.out.println("ID alquiler:");
                    int id = sc.nextInt();
                    System.out.println("ID cliente:");
                    int idCli = sc.nextInt();
                    System.out.println("ID empleado:");
                    int idEmp = sc.nextInt();
                    Alquiler nuevoAlq = new Alquiler(id, idCli, idEmp, null, null, null, 0.0);
                    AlquilerD.crearAlquiler(nuevoAlq);
                    break;
                case 2: // Modificar alquiler
                    System.out.println("ID alquiler a modificar:");
                    int idMod = sc.nextInt();
                    Alquiler alqMod = new Alquiler(idMod, 1, 1, null, null, null, 50.0);
                    AlquilerD.actualizarAlquiler(alqMod);
                    break;
                case 3: // Eliminar alquiler
                    System.out.println("ID alquiler a eliminar:");
                    int idDel = sc.nextInt();
                    new AlquilerD().eliminarAlquiler(idDel);
                    break;
                case 4: // Listar alquileres
                    for (Alquiler a : AlquilerD.listarTodos()) {
                        System.out.println(a);
                    }
                    break;
                }
				break;
            case 4:
                System.out.println("ID categoría:");
                int idCategoria = sc.nextInt();
                try (Connection con = Conexion.conectar();
                     PreparedStatement ps = con.prepareStatement(
                         "SELECT * FROM vehiculos WHERE id_categoria = ?")) {
                    ps.setInt(1, idCategoria); // Inserta el ID en la consulta
                    ResultSet rs = ps.executeQuery(); // Ejecuta la consulta
                    while (rs.next()) { // Recorre los resultados
                        System.out.println(
                            rs.getString("matricula") + " - " +
                            rs.getString("modelo") + " - " +
                            rs.getDouble("precio_dia")
                        );
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;           
				case 5:
                System.out.println("DNI cliente:");
					String dniCliente = sc.next();
                try (Connection con = Conexion.conectar();
                     PreparedStatement ps = con.prepareStatement(
                         "SELECT * FROM alquileres WHERE dni_cliente = ?")) {
                    ps.setString(1, dniCliente); // Inserta DNI en la consulta
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        System.out.println(
                            "ID: " + rs.getInt("id") + " | Inicio: " + rs.getDate("fecha_inicio") + " | Fin: " + rs.getDate("fecha_fin"));
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
				case 6:
                try (Connection con = Conexion.conectar();
                     PreparedStatement ps = con.prepareStatement("SELECT * FROM categorias");
                     ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(rs.getInt("id") + " - " + rs.getString("nombre"));
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
				case 7:
                try (Connection con = Conexion.conectar();
                     PreparedStatement ps = con.prepareStatement("SELECT * FROM empleados");
                     ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(
                            rs.getInt("id") + " - " +
                            rs.getString("nombre") + " - " +
                            rs.getString("cargo")
                        );
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;            
				case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción inválida");
            }
        } while (opcion != 0);
        sc.close();
    }
}
