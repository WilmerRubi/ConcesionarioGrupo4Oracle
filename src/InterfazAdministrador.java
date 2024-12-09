import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InterfazAdministrador {
//Interfaz para insertar un cliente en la tabla clientes de la bd
    public static void insertarCliente(Scanner sc) {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = sc.next();
        System.out.print("Ingrese la dirección del cliente: ");
        String direccion = sc.next();
        System.out.print("Ingrese el número de teléfono del cliente: ");
        int noTelefono = sc.nextInt();
        System.out.print("Ingrese el sexo del cliente: ");
        String sexo = sc.next();
        System.out.print("Ingrese los ingresos anuales del cliente: ");
        double ingresosAnuales = sc.nextDouble();
        
        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL InsertarCliente(?, ?, ?, ?, ?)}");
            cs.setString(1, nombre);
            cs.setString(2, direccion);
            cs.setInt(3, noTelefono);
            cs.setString(4, sexo);
            cs.setDouble(5, ingresosAnuales);
            cs.execute();
            System.out.println("Cliente insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertarModelo(Scanner sc) {
        System.out.print("Ingrese el nombre del modelo: ");
        String nombre = sc.next();
        System.out.print("Ingrese el estilo de carrocería del modelo: ");
        String estiloCarroceria = sc.next();
        System.out.print("Ingrese la marca del modelo: ");
        String marca = sc.next();

        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL InsertarModelo(?, ?, ?)}");
            cs.setString(1, nombre);
            cs.setString(2, estiloCarroceria);
            cs.setString(3, marca);
            cs.execute();
            System.out.println("Modelo insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//Interfaz para insertar una planta  en la tabla planta de la bd

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
            System.out.println("Planta insertada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//Interfaz para insertar un concesionario en la tabla concesionario de la bd
    public static void insertarConcesionario(Scanner sc) {
        System.out.print("Ingrese el nombre del concesionario: ");
        String nombre = sc.next();
        System.out.print("Ingrese la dirección del concesionario: ");
        String direccion = sc.next();
        System.out.print("Ingrese el número de teléfono del concesionario: ");
        int noTelefono = sc.nextInt();

        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL InsertarConcesionario(?, ?, ?)}");
            cs.setString(1, nombre);
            cs.setString(2, direccion);
            cs.setInt(3, noTelefono);
            cs.execute();
            System.out.println("Concesionario insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//Interfaz para insertar un proveedor en la tabla proveedor de la bd
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
            System.out.println("Proveedor insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//Menu de la interfaz de administradores
    public static void menuAdministrador(Scanner sc) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--------------------------------");
            System.out.println("Menú Administrador:");
            System.out.println("1. Insertar Cliente");
            System.out.println("2. Insertar Modelo");
            System.out.println("3. Insertar Planta");
            System.out.println("4. Insertar Concesionario");
            System.out.println("5. Insertar Proveedor");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    insertarCliente(sc);
                    break;
                case 2:
                    insertarModelo(sc);
                    break;
                case 3:
                    insertarPlanta(sc);
                    break;
                case 4:
                    insertarConcesionario(sc);
                    break;
                case 5:
                    insertarProveedor(sc);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
