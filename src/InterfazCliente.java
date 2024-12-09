import java.sql.*;
import java.util.Scanner;

public class InterfazCliente {

    // Metodo para consultar distribuidores y sus vehículos disponibles utilizando la vista InventarioPromedio
    public static void consultarDistribuidores() {
        String query = "SELECT concesionario, tiempo_promedio_inventario FROM InventarioPromedio ORDER BY tiempo_promedio_inventario DESC";
        
        try (Connection conn = ConexionBD.conectar(); 
             PreparedStatement ps = conn.prepareStatement(query); 
             ResultSet rs = ps.executeQuery()) {
            
            System.out.println("Distribuidores disponibles:");
            while (rs.next()) {
                System.out.println("Concesionario: " + rs.getString("concesionario") +
                                   ", Tiempo Promedio en Inventario (días): " + rs.getDouble("tiempo_promedio_inventario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar los vehículos disponibles en un concesionario con precios
    public static void consultarVehiculosPorConcesionario(Scanner sc) {
        System.out.print("Ingrese el nombre del concesionario: ");
        String concesionario = sc.nextLine();
        
        String query = "SELECT VE.VIN, M.marca, M.estiloCarroceria, VE.precio, VE.fechaIngreso " +
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

    // Método para mostrar los productos más vendidos utilizando la vista TendenciasVentas
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
                                   ", Sexo: " + rs.getString("sexo") +
                                   ", Rango de Ingresos: " + rs.getString("rango_ingresos") +
                                   ", Ventas: " + rs.getInt("cantidad_ventas") +
                                   ", Total Ventas: $" + rs.getDouble("total_ventas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Menu de la interfaz clientes

    public static void menuCliente(Scanner sc) {  
        boolean exit = false;

        while (!exit) {
            System.out.println("--------------------------------");
            System.out.println("Menú Cliente:");
            System.out.println("1. Consultar Distribuidores");
            System.out.println("2. Consultar Vehículos en Concesionarios");
            System.out.println("3. Ver Tendencias de Ventas");
            System.out.println("4. Salir");
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
                        consultarTendenciasVentas();
                        break;
                    case 4:
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
        menuCliente(sc); 
    }
}
