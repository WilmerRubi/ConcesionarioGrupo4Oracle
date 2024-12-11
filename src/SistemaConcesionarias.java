import java.sql.*;
import java.util.Scanner;

public class SistemaConcesionarias {

    // Método para autenticar usuario
    public static boolean autenticarUsuario(String nombreUsuario, String contrasena, Scanner sc) {
        String query = "SELECT tipoUsuario, idUsuario FROM Usuario WHERE nombre = ? AND contrasena = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipoUsuario = rs.getString("tipoUsuario");
                    int idUsuario = rs.getInt("idUsuario"); 

                    // Redirige al menu correspondiente según el tipo de usuario
                    switch (tipoUsuario) {
                        case "admin":
                            InterfazAdministrador.menuAdministrador(nombreUsuario, sc);
                            break;
                        case "cliente":
                            InterfazCliente.menuCliente(nombreUsuario, idUsuario, sc);
                            break;
                        case "marketing":
                            InterfazMarketing.menuMarketing(sc); 
                            break;
                        default:
                            System.out.println("Tipo de usuario invalido");
                            break;
                    }
                    return true;  
                } else {
                    System.out.println("Credenciales incorrectas.");
                    return false; 
                }
            }
        } catch (SQLException e) {
            System.out.println("Error de conexion a la base de datos");
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  

        boolean autenticado = false;

        // Bucle para seguir pidiendo credenciales hasta que el usuario inicie sesion
        while (!autenticado) {
            System.out.println("Bienvenido al sistema de concesionarios.");
            System.out.print("Ingrese su nombre de usuario: ");
            String nombreUsuario = sc.nextLine();

            System.out.print("Ingrese su contraseña: ");
            String contrasena = sc.nextLine();

            // Intentar autenticar al usuario
            autenticado = autenticarUsuario(nombreUsuario, contrasena, sc);  
            if (!autenticado) {
                System.out.println("Autenticacion fallida. Intente nuevamente");
            } else {
                System.out.println("Autenticacion exitosa.");
            }
        }

        sc.close(); 
    }
}
