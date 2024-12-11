import java.sql.*;
import java.util.Scanner;

public class BuscarConcesionariosYModelos {

    // Metodo para registrar las busquedas de modelos 
    public static void registrarBusquedaModelo(String modelo, int idUsuario) {
        String query = "INSERT INTO BUSQUEDASMODELOS (MODELO, FECHABUSQUEDA, ID_USUARIO) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, modelo);
            ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            ps.setInt(3, idUsuario); 
            ps.executeUpdate();
            System.out.println("Búsqueda registrada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar la búsqueda.");
            e.printStackTrace();
        }
    }

    // Metodo para buscar concesionarios que tienen un modelo 
    public static void buscarConcesionariosPorModelo(Scanner sc, int idUsuario) {
        System.out.print("Ingrese el nombre del modelo de coche: ");
        String modelo = sc.nextLine();
        modelo = modelo.toLowerCase();

        String query = "SELECT m.nombre AS modelo, p.nombre AS planta, c.nombre AS concesionario, v.VIN, ve.precio " +
                       "FROM Vehiculos v " +
                       "JOIN Modelos m ON v.idModelo = m.idModelo " +
                       "JOIN ModelosxPlantas mp ON m.idModelo = mp.idModelo " +
                       "JOIN Plantas p ON mp.idPlanta = p.idPlanta " +
                       "JOIN VehiculosxConcesionarios vc ON v.VIN = vc.VIN " +
                       "JOIN Concesionarios c ON vc.idConcesionario = c.idConcesionario " +
                       "JOIN Ventas ve ON v.VIN = ve.VIN " +
                       "WHERE LOWER(m.nombre) = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, modelo);

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Concesionarios con el modelo " + modelo + ":");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Modelo: " + rs.getString("modelo") +
                                       ", Planta: " + rs.getString("planta") +
                                       ", Concesionario: " + rs.getString("concesionario") +
                                       ", VIN: " + rs.getString("VIN") +
                                       ", Precio: " + rs.getDouble("precio"));
                }
                if (!found) {
                    System.out.println("Modelo no disponible en concesionarios.");
                } else {
                    registrarBusquedaModelo(modelo, idUsuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para buscar concesionarios cercanos por marca
    public static void buscarConcesionariosCercanos(Scanner sc) {
        System.out.print("Ingrese la marca del vehículo: ");
        String marca = sc.next();
        marca = marca.toLowerCase();

        String query = "SELECT m.nombre AS modelo, p.nombre AS planta, c.nombre AS concesionario, v.VIN, ve.precio " +
                       "FROM Vehiculos v " +
                       "JOIN Modelos m ON v.idModelo = m.idModelo " +
                       "JOIN ModelosxPlantas mp ON m.idModelo = mp.idModelo " +
                       "JOIN Plantas p ON mp.idPlanta = p.idPlanta " +
                       "JOIN VehiculosxConcesionarios vc ON v.VIN = vc.VIN " +
                       "JOIN Concesionarios c ON vc.idConcesionario = c.idConcesionario " +
                       "JOIN Ventas ve ON v.VIN = ve.VIN " +
                       "WHERE LOWER(m.marca) = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, marca);

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Concesionarios con la marca " + marca + ":");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Modelo: " + rs.getString("modelo") + 
                                       ", Planta: " + rs.getString("planta") +
                                       ", Concesionario: " + rs.getString("concesionario") + 
                                       ", VIN: " + rs.getString("VIN") + 
                                       ", Precio: " + rs.getDouble("precio"));
                }
                if (!found) {
                    System.out.println("No se encontraron concesionarios con esa marca");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para buscar los modelos disponibles en un concesionario 
    public static void buscarModelosPorConcesionario(Scanner sc) {
        System.out.print("Ingrese el nombre del concesionario: ");
        String concesionarioNombre = sc.nextLine();
        concesionarioNombre = concesionarioNombre.toLowerCase();
        String query = "SELECT m.nombre AS modelo " +
                       "FROM Vehiculos v " +
                       "JOIN Modelos m ON v.idModelo = m.idModelo " +
                       "JOIN VehiculosxConcesionarios vc ON v.VIN = vc.VIN " +
                       "JOIN Concesionarios c ON vc.idConcesionario = c.idConcesionario " +
                       "WHERE LOWER(c.nombre) = ?"; 

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, concesionarioNombre); 

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Modelos disponibles en el concesionario " + concesionarioNombre + ":");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Modelo: " + rs.getString("modelo"));
                }
                if (!found) {
                    System.out.println("No se encontraron modelos disponibles en ese concesionario");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menu de la interfaz de busqueda de concesionarios y modelos
    public static void menuBusqueda(Scanner sc, int idUsuario) {
        boolean exit = false;
        while (!exit) {
            System.out.println("----------------------------------------------");
            System.out.println("Menú de Búsqueda:");
            System.out.println("1. Buscar concesionarios cercanos por marca");
            System.out.println("2. Buscar modelos disponibles en un concesionario");
            System.out.println("3. Buscar concesionarios por modelo");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    buscarConcesionariosCercanos(sc);
                    break;
                case 2:
                    buscarModelosPorConcesionario(sc);
                    break;
                case 3:
                    buscarConcesionariosPorModelo(sc, idUsuario);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese su ID de usuario: ");
        int idUsuario = sc.nextInt();
        sc.nextLine(); 
        menuBusqueda(sc, idUsuario);
    }
}
