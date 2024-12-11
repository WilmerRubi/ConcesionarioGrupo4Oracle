import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean autenticado = false;

        // Bucle para seguir pidiendo credenciales hasta que el usuario inicie sesion
        while (!autenticado) {
            System.out.println("Bienvenido al sistema de gestion de concesionarios");
            System.out.println("Para continuar, inicie sesion");
            System.out.print("Ingrese su nombre: ");
            String nombreUsuario = sc.nextLine();

            System.out.print("Ingrese su contraseña: ");
            String contrasena = sc.nextLine();

            // Llamar al metodo de autenticacion desde SistemaConcesionarias
            autenticado = SistemaConcesionarias.autenticarUsuario(nombreUsuario, contrasena, sc);

            if (!autenticado) {
                System.out.println("Autenticacion fallida, intente nuevamente");
            } else {
                System.out.println("Autenticacion correcta, bienvenido");
            }
        }

        sc.close();  
    }
}
