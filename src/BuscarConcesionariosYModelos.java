import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class BuscarConcesionariosYModelos {

    // metodo para registrar las busquedas de modelos
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

    // metodo para buscar concesionarios que tienen un modelo
    public static void buscarConcesionariosPorModelo(JTextArea textArea, int idUsuario) {
        String modelo = JOptionPane.showInputDialog("Ingrese el nombre del modelo de coche:");
        if (modelo == null || modelo.isEmpty()) {
            textArea.setText("Debe ingresar un modelo válido.");
            return;
        }
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
                StringBuilder result = new StringBuilder("Concesionarios con el modelo " + modelo + ":\n");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    result.append("Modelo: ").append(rs.getString("modelo"))
                          .append(", Planta: ").append(rs.getString("planta"))
                          .append(", Concesionario: ").append(rs.getString("concesionario"))
                          .append(", VIN: ").append(rs.getString("VIN"))
                          .append(", Precio: $").append(rs.getDouble("precio")).append("\n");
                }
                if (!found) {
                    result.append("Modelo no disponible en concesionarios.");
                } else {
                    registrarBusquedaModelo(modelo, idUsuario);
                }
                textArea.setText(result.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para buscar concesionarios cercanos por marca
    public static void buscarConcesionariosCercanos(JTextArea textArea) {
        String marca = JOptionPane.showInputDialog("Ingrese la marca del vehículo:");
        if (marca == null || marca.isEmpty()) {
            textArea.setText("Debe ingresar una marca válida.");
            return;
        }
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
                StringBuilder result = new StringBuilder("Concesionarios con la marca " + marca + ":\n");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    result.append("Modelo: ").append(rs.getString("modelo"))
                          .append(", Planta: ").append(rs.getString("planta"))
                          .append(", Concesionario: ").append(rs.getString("concesionario"))
                          .append(", VIN: ").append(rs.getString("VIN"))
                          .append(", Precio: $").append(rs.getDouble("precio")).append("\n");
                }
                if (!found) {
                    result.append("No se encontraron concesionarios con esa marca.");
                }
                textArea.setText(result.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para buscar los modelos disponibles en un concesionario
    public static void buscarModelosPorConcesionario(JTextArea textArea) {
        String concesionarioNombre = JOptionPane.showInputDialog("Ingrese el nombre del concesionario:");
        if (concesionarioNombre == null || concesionarioNombre.isEmpty()) {
            textArea.setText("Debe ingresar un concesionario válido.");
            return;
        }
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
                StringBuilder result = new StringBuilder("Modelos disponibles en el concesionario " + concesionarioNombre + ":\n");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    result.append("Modelo: ").append(rs.getString("modelo")).append("\n");
                }
                if (!found) {
                    result.append("No se encontraron modelos disponibles en ese concesionario.");
                }
                textArea.setText(result.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para mostrar el menu de busqueda
    public static void menuBusquedaGUI() {
        JFrame frame = new JFrame("Buscar Concesionarios y Modelos");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
        JTextArea textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);
    
        JButton buscarMarcaButton = new JButton("Buscar concesionarios cercanos por marca");
        buscarMarcaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarConcesionariosCercanos(textArea);
            }
        });
    
        JButton buscarModeloButton = new JButton("Buscar modelos disponibles en un concesionario");
        buscarModeloButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarModelosPorConcesionario(textArea);
            }
        });
    
        JButton buscarConcesionariosButton = new JButton("Buscar concesionarios por modelo");
        buscarConcesionariosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarConcesionariosPorModelo(textArea, 0); // Aquí ya no se necesita ID de usuario
            }
        });
    
        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    
        panel.add(buscarMarcaButton);
        panel.add(buscarModeloButton);
        panel.add(buscarConcesionariosButton);
        panel.add(salirButton);
    
        frame.add(panel);
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        menuBusquedaGUI(); 
    }
    public static void menuBusqueda(JTextArea textArea, int idUsuario) {
        throw new UnsupportedOperationException("Unimplemented method 'menuBusqueda'");
    }
}
