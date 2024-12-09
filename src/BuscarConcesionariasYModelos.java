import java.sql.*;
import java.util.Scanner;

public class BuscarConcesionariasYModelos {

    public static void buscarConcesionariasCercanas(Scanner sc) {
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
                System.out.println("Concesionarias con la marca " + marca + ":");
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
                    System.out.println("No se encontraron concesionarias con esa marca.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar los modelos disponibles en un concesionario específico
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
                    System.out.println("No se encontraron modelos disponibles en ese concesionario.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menú de la interfaz de búsqueda de concesionarias y modelos
    public static void menuBusqueda(Scanner sc) {
        boolean exit = false;
        while (!exit) {
            System.out.println("----------------------------------------------");
            System.out.println("Menú de Búsqueda:");
            System.out.println("1. Buscar Marca en concesionarios ");
            System.out.println("2. Buscar modelos disponibles en un concesionario");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    buscarConcesionariasCercanas(sc);
                    break;
                case 2:
                    buscarModelosPorConcesionario(sc);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        menuBusqueda(sc);
    }
}
