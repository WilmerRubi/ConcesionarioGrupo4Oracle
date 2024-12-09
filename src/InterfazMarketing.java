import java.sql.*;
import java.util.Scanner;

public class InterfazMarketing {

    // Método para mostrar las tendencias de ventas
    public static void mostrarTendenciasVentas() {
        String query = "SELECT anio, mes, semana, marca, sexo, rango_ingresos, cantidad_ventas, total_ventas " +
                       "FROM TendenciasVentas " +
                       "WHERE anio >= EXTRACT(YEAR FROM SYSDATE) - 3 " +
                       "ORDER BY anio DESC, mes DESC, semana DESC";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Tendencias de ventas de marcas (últimos 3 años):");
            System.out.printf("%-5s %-5s %-10s %-20s %-10s %-15s %-10s %-15s%n", 
                              "Año", "Mes", "Semana", "Marca", "Género", "Rango Ingresos", "Ventas", "Total Ventas");

            while (rs.next()) {
                System.out.printf("%-5d %-5d %-10s %-20s %-10s %-15s %-10d $%-15.2f%n",
                                  rs.getInt("anio"),
                                  rs.getInt("mes"),
                                  rs.getString("semana"),
                                  rs.getString("marca"),
                                  rs.getString("sexo"),
                                  rs.getString("rango_ingresos"),
                                  rs.getInt("cantidad_ventas"),
                                  rs.getDouble("total_ventas"));
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar las tendencias de ventas.");
            e.printStackTrace();
        }
    }

    // Método para mostrar las transmisiones defectuosas
    public static void mostrarTransmisionesDefectuosas() {
        String query = "SELECT VIN, idModelo, transmision, fechaIngreso, cliente, idCliente, proveedor, defectuosa " +
                       "FROM VehiculosConTransmisionesDefectuosas";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Vehículos con transmisiones defectuosas:");
            System.out.printf("%-17s %-10s %-15s %-15s %-20s %-10s %-15s %-10s%n", 
                              "VIN", "Modelo", "Transmisión", "Fecha Ingreso", 
                              "Cliente", "ID Cliente", "Proveedor", "Defectuosa");

            while (rs.next()) {
                System.out.printf("%-17s %-10s %-15s %-15s %-20s %-10s %-15s %-10s%n",
                                  rs.getString("VIN"),
                                  rs.getString("idModelo"),
                                  rs.getString("transmision"),
                                  rs.getDate("fechaIngreso"),
                                  rs.getString("cliente"),
                                  rs.getString("idCliente"),
                                  rs.getString("proveedor"),
                                  rs.getString("defectuosa"));
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar las transmisiones defectuosas.");
            e.printStackTrace();
        }
    }

    // Método para mostrar las dos mejores marcas por cantidad de dolares vendidos
    public static void mostrarTop2MarcasDolares() {
        String query = "SELECT marca, total_ventas " +
                       "FROM Top2MarcasDolares " +
                       "ORDER BY total_ventas DESC";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Top 2 marcas por ventas en dólares (último año):");
            System.out.printf("%-20s %-15s%n", "Marca", "Total Ventas");

            while (rs.next()) {
                System.out.printf("%-20s $%-15.2f%n",
                                  rs.getString("marca"),
                                  rs.getDouble("total_ventas"));
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar el Top 2 de marcas por ventas en dólares.");
            e.printStackTrace();
        }
    }

    // Método para mostrar las dos mejores marcas por unidades vendidos
    public static void mostrarTop2MarcasUnidades() {
        String query = "SELECT marca, total_unidades " +
                       "FROM Top2MarcasUnidades " +
                       "ORDER BY total_unidades DESC";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Top 2 marcas por unidades vendidas (último año):");
            System.out.printf("%-20s %-10s%n", "Marca", "Total Unidades");

            while (rs.next()) {
                System.out.printf("%-20s %-10d%n",
                                  rs.getString("marca"),
                                  rs.getInt("total_unidades"));
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar el Top 2 de marcas por unidades vendidas.");
            e.printStackTrace();
        }
    }

    // Método para mostrar el mes con mayores ventas de vehículos convertibles
    public static void mostrarMejorMesConvertibles() {
        String query = "SELECT mes, total_convertibles " +
                       "FROM MejorMesConvertibles " +
                       "ORDER BY total_convertibles DESC";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Mes con mejores ventas de convertibles:");
            if (rs.next()) {
                System.out.printf("Mes: %d, Ventas de Convertibles: %d%n",
                                  rs.getInt("mes"),
                                  rs.getInt("total_convertibles"));
            } else {
                System.out.println("No se encontraron ventas de convertibles.");
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar el mejor mes para convertibles.");
            e.printStackTrace();
        }
    }

    // Método para mostrar los distribuidores con el mayor tiempo promedio en inventario
    public static void mostrarDistribuidoresConMayorTiempoInventario() {
        String query = "SELECT concesionario, tiempo_promedio_inventario " +
                       "FROM InventarioPromedio " +
                       "ORDER BY tiempo_promedio_inventario DESC";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Distribuidores con el mayor tiempo promedio en inventario:");
            System.out.printf("%-20s %-10s%n", "Concesionario", "Tiempo Promedio (días)");

            while (rs.next()) {
                System.out.printf("%-20s %-10.2f%n",
                                  rs.getString("concesionario"),
                                  rs.getDouble("tiempo_promedio_inventario"));
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar los distribuidores con mayor tiempo promedio en inventario.");
            e.printStackTrace();
        }
    }

    // Menú principal del departamento de marketing
    public static void menuMarketing(Scanner sc) {
        boolean exit = false;

        while (!exit) {
            System.out.println("--------------------------------");
            System.out.println("Menú Departamento de Marketing:");
            System.out.println("1. Ver Tendencias de Marketing");
            System.out.println("2. Ver Transmisiones Defectuosas");
            System.out.println("3. Ver Top 2 Marcas por Ventas en Dólares");
            System.out.println("4. Ver Top 2 Marcas por Unidades Vendidas");
            System.out.println("5. Ver Mejor Mes para Convertibles");
            System.out.println("6. Ver Distribuidores con Mayor Tiempo en Inventario");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            
            if (sc.hasNextInt()) {
                int opcion = sc.nextInt();
                sc.nextLine(); 
                switch (opcion) {
                    case 1:
                        mostrarTendenciasVentas();
                        break;
                    case 2:
                        mostrarTransmisionesDefectuosas();
                        break;
                    case 3:
                        mostrarTop2MarcasDolares();
                        break;
                    case 4:
                        mostrarTop2MarcasUnidades();
                        break;
                    case 5:
                        mostrarMejorMesConvertibles();
                        break;
                    case 6:
                        mostrarDistribuidoresConMayorTiempoInventario();
                        break;
                    case 7:
                        exit = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Entrada no válida, por favor ingrese un número.");
                sc.next(); 
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        menuMarketing(sc);  
    }
}
