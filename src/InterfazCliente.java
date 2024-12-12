import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.util.Scanner;

public class InterfazCliente extends JFrame {
    public InterfazCliente() {
    }

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
    //metodo para consultar distribuidores
    public static void consultarDistribuidores(JTextArea textArea) {
        String query = "SELECT concesionario FROM InventarioPromedio ORDER BY tiempo_promedio_inventario DESC";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            StringBuilder result = new StringBuilder("Distribuidores disponibles:\n");
            while (rs.next()) {
                result.append("Concesionario: ").append(rs.getString("concesionario")).append("\n");
            }
            textArea.setText(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //metodo para consultar los proveedores
    public static void consultarProveedores(JTextArea textArea) {
        String query = "SELECT nombre, direccion FROM Proveedores ORDER BY nombre";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            StringBuilder result = new StringBuilder("Proveedores disponibles:\n");
            while (rs.next()) {
                result.append("Nombre: ").append(rs.getString("nombre"))
                        .append(", Dirección: ").append(rs.getString("direccion")).append("\n");
            }
            textArea.setText(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //metodo para los vehiculos en un concesionario
    public static void consultarVehiculosPorConcesionario(JTextArea textArea, String concesionario) {
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
                StringBuilder result = new StringBuilder("Vehículos disponibles en " + concesionario + ":\n");
                if (rs.next()) {
                    do {
                        result.append("VIN: ").append(rs.getString("VIN"))
                                .append(", Marca: ").append(rs.getString("marca"))
                                .append(", Modelo: ").append(rs.getString("nombreModelo"))
                                .append(", Estilo: ").append(rs.getString("estiloCarroceria"))
                                .append(", Precio: $").append(rs.getDouble("precio"))
                                .append(", Fecha de Ingreso: ").append(rs.getDate("fechaIngreso")).append("\n");
                    } while (rs.next());
                } else {
                    result.append("No se encontraron vehículos disponibles en este concesionario.");
                }
                textArea.setText(result.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //metodo para consultar las tendencias, carros mas vendidos
    public static void consultarTendenciasVentas(JTextArea textArea) {
        String query = "SELECT anio, mes, semana, marca, sexo, rango_ingresos, cantidad_ventas, total_ventas " +
                "FROM TendenciasVentas ORDER BY total_ventas DESC FETCH FIRST 5 ROWS ONLY";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            StringBuilder result = new StringBuilder("Tendencias de Ventas (Top 5):\n");
            while (rs.next()) {
                result.append("Año: ").append(rs.getInt("anio"))
                        .append(", Mes: ").append(rs.getInt("mes"))
                        .append(", Semana: ").append(rs.getString("semana"))
                        .append(", Marca: ").append(rs.getString("marca"))
                        .append(", Ventas: ").append(rs.getInt("cantidad_ventas"))
                        .append(", Precio Vehiculo: $").append(rs.getDouble("total_ventas")).append("\n");
            }
            textArea.setText(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void menuClienteGUI(String nombreUsuario, int idUsuario) {

        JFrame clienteDashboard = new JFrame("Menu clientes");
        clienteDashboard.setSize(600, 400);
        clienteDashboard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clienteDashboard.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JFrame frame = new JFrame("Interfaz Cliente");
        frame.setSize(600, 400);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Bienvenido, " + nombreUsuario);
        panel.add(label);

        JTextArea textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        JButton consultarDistribuidoresButton = new JButton("Consultar distribuidores");
        consultarDistribuidoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarDistribuidores(textArea);
            }
        });

        JButton consultarVehiculosButton = new JButton("Consultar vehículos en concesionarios");
        consultarVehiculosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String concesionario = JOptionPane.showInputDialog("Ingrese el nombre del concesionario:");
                consultarVehiculosPorConcesionario(textArea, concesionario);
            }
        });
        

        JButton consultarProveedoresButton = new JButton("Ver proveedores y sus ubicaciones");
        consultarProveedoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarProveedores(textArea);
            }
        });

        JButton tendenciasVentasButton = new JButton("Ver tendencias de ventas");
        tendenciasVentasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarTendenciasVentas(textArea);
            }
        });
        JButton buscarConcesionariosYModelosButton = new JButton("Buscar Concesionarios y Modelos");
        buscarConcesionariosYModelosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Llamada a la función que abrirá el menú de búsqueda de concesionarios
                BuscarConcesionariosYModelos.menuBusquedaGUI();  // Asegúrate de tener este método previamente implementado
            }
        });
        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(consultarDistribuidoresButton);
        panel.add(consultarVehiculosButton);
        panel.add(consultarProveedoresButton);
        panel.add(tendenciasVentasButton);
        panel.add(buscarConcesionariosYModelosButton);  
        panel.add(salirButton);

        clienteDashboard.add(panel);
        clienteDashboard.setLocationRelativeTo(null);
        clienteDashboard.setVisible(true);
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int idUsuario = hacerLogin(sc);  
        if (idUsuario != -1) {
            System.out.print("Ingrese su nombre de usuario: ");
            String nombreUsuario = sc.nextLine();  
            menuClienteGUI(nombreUsuario, idUsuario);  
        } else {
            System.out.println("Login fallido. No se puede acceder a la interfaz.");
        }
    }
}

class ConexionBD {
    public static Connection conectar() throws SQLException {
        try {
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "usuario", "contraseña");
        } catch (SQLException e) {
            throw new SQLException("Error de conexión", e);
        }
    }
}
