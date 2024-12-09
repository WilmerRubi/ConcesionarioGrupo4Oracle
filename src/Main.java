import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("--------------------------------");
            System.out.println("Menú Principal:");
            System.out.println("1. Acceder como Administrador");
            System.out.println("2. Acceder como Servicio de Localización de Vehículos");
            System.out.println("3. Acceder como Cliente");
            System.out.println("4. Acceder como Departamento de Marketing");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            if (sc.hasNextInt()) { // Comprobar que la entrada sea un número entero
                int opcion = sc.nextInt();
                //Switch que interactua con el menu
                switch (opcion) {
                    case 1:
                        InterfazAdministrador.menuAdministrador(sc); 
                        break;
                    case 2:
                        BuscarConcesionariasYModelos.menuBusqueda(sc);  
                        break;
                    case 3:
                        InterfazCliente.menuCliente(sc); 
                        break;
                    case 4:
                        InterfazMarketing.menuMarketing(sc);  
                        break;
                    case 5:
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

        sc.close(); 
    }
}
