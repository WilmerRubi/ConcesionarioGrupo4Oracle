import java.sql.*;
import java.util.Scanner;

public class InterfazAdministrador {

    // Metodo para insertar un cliente en la tabla clientes de la BD
    public static void insertarCliente(Scanner sc) {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = sc.next();
        System.out.print("Ingrese la dirección del cliente: ");
        String direccion = sc.next();
        System.out.print("Ingrese el numero de teléfono del cliente: ");
        int noTelefono = sc.nextInt();
        System.out.print("Ingrese el sexo del cliente: ");
        String sexo = sc.next();
        System.out.print("Ingrese los ingresos anuales del cliente: ");
        double ingresosAnuales = sc.nextDouble();
        System.out.print("Ingrese el ID del usuario asignado al cliente: ");
        int idUsuario = sc.nextInt();
        
        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL InsertarClienteConUsuario(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, nombre);
            cs.setString(2, direccion);
            cs.setInt(3, noTelefono);
            cs.setString(4, sexo);
            cs.setDouble(5, ingresosAnuales);
            cs.setInt(6, idUsuario);
            cs.execute();
            System.out.println("Cliente registrado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para insertar un modelo en la BD
    public static void insertarModelo(Scanner sc) {
        System.out.print("Ingrese el nombre del modelo: ");
        String nombre = sc.next();
        System.out.print("Ingrese el estilo de carroceria del modelo: ");
        String estiloCarroceria = sc.next();
        System.out.print("Ingrese la marca del modelo: ");
        String marca = sc.next();

        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL InsertarModelo(?, ?, ?)}");
            cs.setString(1, nombre);
            cs.setString(2, estiloCarroceria);
            cs.setString(3, marca);
            cs.execute();
            System.out.println("Modelo registrado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para insertar una planta en la BD
    public static void insertarPlanta(Scanner sc) {
        System.out.print("Ingrese el nombre de la planta: ");
        String nombre = sc.next();
        System.out.print("Ingrese la ubicación de la planta: ");
        String ubicacion = sc.next();

        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL InsertarPlanta(?, ?)}");
            cs.setString(1, nombre);
            cs.setString(2, ubicacion);
            cs.execute();
            System.out.println("Planta registrada ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para insertar un concesionario en la BD
    public static void insertarConcesionario(Scanner sc) {
        System.out.print("Ingrese el nombre del concesionario: ");
        String nombre = sc.next();
        System.out.print("Ingrese la direccion del concesionario: ");
        String direccion = sc.next();
        System.out.print("Ingrese el numero de teléfono del concesionario: ");
        int noTelefono = sc.nextInt();

        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL InsertarConcesionario(?, ?, ?)}");
            cs.setString(1, nombre);
            cs.setString(2, direccion);
            cs.setInt(3, noTelefono);
            cs.execute();
            System.out.println("Concesionario registrado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para insertar un proveedor en la BD
    public static void insertarProveedor(Scanner sc) {
        System.out.print("Ingrese el nombre del proveedor: ");
        String nombre = sc.next();
        System.out.print("Ingrese la dirección del proveedor: ");
        String direccion = sc.next();
        System.out.print("Ingrese el número de teléfono del proveedor: ");
        int noTelefono = sc.nextInt();

        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL InsertarProveedor(?, ?, ?)}");
            cs.setString(1, nombre);
            cs.setString(2, direccion);
            cs.setInt(3, noTelefono);
            cs.execute();
            System.out.println("Proveedor registrado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para insertar un usuario
    public static void insertarUsuario(Scanner sc) {
        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = sc.next();
        System.out.print("Ingrese la contraseña del usuario: ");
        String contrasena = sc.next();
        System.out.println("Seleccione el rol del usuario:");
        System.out.println("1. Administrador");
        System.out.println("2. Cliente");
        System.out.println("3. Marketing");
        System.out.print("Ingrese el numero correspondiente al rol: ");
        int opcion = sc.nextInt();
        String tipoUsuario = "";

        switch (opcion) {
            case 1:
                tipoUsuario = "admin";
                break;
            case 2:
                tipoUsuario = "cliente";
                break;
            case 3:
                tipoUsuario = "marketing";
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL InsertarUsuario(?, ?, ?)}");
            cs.setString(1, nombre);
            cs.setString(2, contrasena);
            cs.setString(3, tipoUsuario);
            cs.execute();
            System.out.println("Usuario creado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para ver las entradas de la bitacora
    public static void verBitacora(Scanner sc) {
        try (Connection conn = ConexionBD.conectar()) {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM ADMIN.BITACORA";
            ResultSet rs = stmt.executeQuery(query);
            
            System.out.println("Bitacora de acciones:");
            while (rs.next()) {
                int idRegistro = rs.getInt("IDREGISTRO");
                String accion = rs.getString("ACCION");
                String tabla = rs.getString("TABLA");
                String registro = rs.getString("REGISTRO");
                Timestamp fecha = rs.getTimestamp("FECHA");

                System.out.println("ID: " + idRegistro + ", Acción: " + accion + ", Tabla: " + tabla + 
                                   ", Registro: " + registro + ", Fecha: " + fecha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// Metodo para registrar una venta
public static void registrarVenta(Scanner sc) {
    sc.nextLine();  
    System.out.print("Ingrese el VIN del vehículo que desea vender: ");
    String vin = sc.nextLine();  

    System.out.print("Ingrese el ID del concesionario: ");
    int idConcesionario = sc.nextInt();
    sc.nextLine();  

    System.out.print("Ingrese el ID del cliente: ");
    int idCliente = sc.nextInt();
    sc.nextLine();  

    System.out.print("Ingrese el precio de la venta: ");
    double precio = sc.nextDouble();
    sc.nextLine();  

   
    Date fechaVenta = new Date(System.currentTimeMillis());

    try (Connection conn = ConexionBD.conectar()) {
        String sqlVenta = "INSERT INTO ADMIN.VENTAS (FECHA, IDCONCESIONARIO, IDCLIENTE, VIN, PRECIO) " +
                          "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement psVenta = conn.prepareStatement(sqlVenta)) {
            psVenta.setDate(1, fechaVenta);
            psVenta.setInt(2, idConcesionario);
            psVenta.setInt(3, idCliente);
            psVenta.setString(4, vin);
            psVenta.setDouble(5, precio);
            psVenta.executeUpdate();
            System.out.println("Venta registrada correctamente.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // Menú de la interfaz de administradores
    public static boolean menuAdministrador(String nombreUsuario, Scanner sc) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--------------------------------");
            System.out.println("Bienvenido " + nombreUsuario);
            System.out.println("Menu administracion:");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Registrar cliente");
            System.out.println("3. Registrar modelo de vehiculo");
            System.out.println("4. Registrar concesionario");
            System.out.println("5. Registrar proveedor");
            System.out.println("6. Ver bitacora");
            System.out.println("7. Registrar planta");
            System.out.println("8. Registrar venta"); 
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    insertarUsuario(sc);
                    break;
                case 2:
                    insertarCliente(sc);
                    break;
                case 3:
                    insertarModelo(sc);
                    break;
                case 4:
                    insertarConcesionario(sc);
                    break;
                case 5:
                    insertarProveedor(sc);
                    break;
                case 6:
                    verBitacora(sc);
                    break;
                case 7:
                    insertarPlanta(sc);
                    break;
                case 8:
                    registrarVenta(sc); 
                    break;
                case 9:
                    System.out.println("Cerrando programa");
                    exit = true;
                    return true; 
                default:
                    System.out.println("Opción no válida.");
            }
        }
        return false;
    }
}
