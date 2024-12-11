import java.sql.*;
import java.util.Scanner;

public class InterfazCliente {

    // Metodo para hacer login de cliente
    public static int hacerLogin(Scanner sc) {
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = sc.nextLine();

        System.out.print("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();

        String query = "SELECT idUsuario FROM Usuario WHERE nombre = ? AND contrasena = ? AND tipoUsuario = 'cliente'";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt("idUsuario");
                    System.out.println("Login exitoso. Bienvenido, " + nombreUsuario);
                    return idUsuario; 
                } else {
                    System.out.println("Nombre de usuario o contraseña incorrectos.");
                    return -1; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Metodo para consultar distribuidores
    public static void consultarDistribuidores() {
        String query = "SELECT concesionario FROM InventarioPromedio ORDER BY tiempo_promedio_inventario DESC";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Distribuidores disponibles:");
            while (rs.next()) {
                System.out.println("Concesionario: " + rs.getString("concesionario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para consultar proveedores
    public static void consultarProveedores() {
        String query = "SELECT nombre, direccion FROM Proveedores ORDER BY nombre";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Proveedores disponibles:");
            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString("nombre") + ", Dirección: " + rs.getString("direccion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para consultar vehículos por concesionario
    public static void consultarVehiculosPorConcesionario(Scanner sc) {
        System.out.print("Ingrese el nombre del concesionario: ");
        String concesionario = sc.nextLine();
    
        String query = "SELECT VE.VIN, M.marca, M.estiloCarroceria, VE.precio, VE.fechaIngreso, M.nombre AS nombreModelo " +
                       "FROM Vehiculos VE " +
                       "JOIN Modelos M ON VE.idModelo = M.idModelo " +
                       "JOIN VehiculosxConcesionarios VC ON VE.VIN = VC.VIN " +
                       "JOIN Concesionarios C ON VC.idConcesionario = C.idConcesionario " +
                       "WHERE C.nombre = ? AND VE.VIN NOT IN (SELECT VIN FROM Ventas)";
    
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, concesionario);
    
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Vehículos disponibles en " + concesionario + ":");
                    do {
                        System.out.println("VIN: " + rs.getString("VIN") +
                                           ", Marca: " + rs.getString("marca") +
                                           ", Modelo: " + rs.getString("nombreModelo") + // Mostrar el nombre del modelo
                                           ", Estilo: " + rs.getString("estiloCarroceria") +
                                           ", Precio: $" + rs.getDouble("precio") +
                                           ", Fecha de Ingreso: " + rs.getDate("fechaIngreso"));
                    } while (rs.next());
                } else {
                    System.out.println("No se encontraron vehículos disponibles en este concesionario.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    // Metodo para mostrar tendencias de ventas
    public static void consultarTendenciasVentas() {
        String query = "SELECT anio, mes, semana, marca, sexo, rango_ingresos, cantidad_ventas, total_ventas " +
                       "FROM TendenciasVentas ORDER BY total_ventas DESC FETCH FIRST 5 ROWS ONLY";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Tendencias de Ventas (Top 5):");
            while (rs.next()) {
                System.out.println("Año: " + rs.getInt("anio") + 
                                   ", Mes: " + rs.getInt("mes") + 
                                   ", Semana: " + rs.getString("semana") +
                                   ", Marca: " + rs.getString("marca") +
                                   ", Ventas: " + rs.getInt("cantidad_ventas") +
                                   ", Precio Vehiculo: $" + rs.getDouble("total_ventas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para acceder a la búsqueda de concesionarios y modelos
    public static void buscarConcesionariasYModelos(Scanner sc, int idUsuario) {
        BuscarConcesionariosYModelos.menuBusqueda(sc, idUsuario);
    }

    // Menu de la interfaz cliente
    public static void menuCliente(String nombreUsuario, int idUsuario, Scanner sc) {  
        boolean exit = false;

        while (!exit) {
            System.out.println("--------------------------------");
            System.out.println("Bienvenido " + nombreUsuario);
            System.out.println("Menu Cliente:");
            System.out.println("1. Consultar distribuidores");
            System.out.println("2. Consultar vehículos en concesionarios");
            System.out.println("3. Ver proveedores y sus ubicaciones");
            System.out.println("4. Buscar vehículos");
            System.out.println("5. Ver tendencias de ventas");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            if (sc.hasNextInt()) { 
                int opcion = sc.nextInt();
                sc.nextLine();  
                switch (opcion) {
                    case 1:
                        consultarDistribuidores();
                        break;
                    case 2:
                        consultarVehiculosPorConcesionario(sc);
                        break;
                    case 3:
                        consultarProveedores();
                        break;
                    case 4:
                        buscarConcesionariasYModelos(sc, idUsuario);
                        break;
                    case 5:
                        consultarTendenciasVentas();
                        break;
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Entrada inválida. Debe ser un número entero.");
                sc.next(); 
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int idUsuario = hacerLogin(sc);
        if (idUsuario != -1) {
            String nombreUsuario = sc.nextLine(); 
            menuCliente(nombreUsuario, idUsuario, sc);
        }
    }
}
