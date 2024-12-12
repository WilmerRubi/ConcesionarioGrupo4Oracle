import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class InterfazMarketing extends JFrame {
    public InterfazMarketing() {
    }

    // Metodo para mostrar las tendencias de ventas
    public static void mostrarTendenciasVentas(JTextArea textArea) {
        String query = "SELECT anio, mes, semana, marca, cantidad_ventas, total_ventas " +
                       "FROM TendenciasVentas " +
                       "WHERE anio >= EXTRACT(YEAR FROM SYSDATE) - 3 " +
                       "ORDER BY anio DESC, mes DESC, semana DESC";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
        
            // Limpiar el JTextArea antes de agregar los resultados
            textArea.setText("Tendencias de ventas de marcas (últimos 3 años):\n");
            
            // Cabecera con 10 espacios entre columnas
            textArea.append(String.format("%-6s%-10s%-10s%-30s%-10s%-15s%n", 
                                          "Año", "Mes", "Semana", "Marca", "Ventas", "Total Ventas"));
            
            // Imprimir los resultados con 10 espacios entre columnas
            while (rs.next()) {
                int ventas = rs.getInt("cantidad_ventas");
                double totalVentas = rs.getDouble("total_ventas");
                
                // Alinear correctamente los números con 10 espacios
                textArea.append(String.format("%-6d%-10d%-10s%-30s%-10d$%-14.2f%n",
                                  rs.getInt("anio"),
                                  rs.getInt("mes"),
                                  rs.getString("semana"),
                                  rs.getString("marca"),
                                  ventas,
                                  totalVentas));
            }
        } catch (SQLException e) {
            textArea.append("Error al mostrar las tendencias de ventas\n");
            e.printStackTrace();
        }
    }
    // Metodo para mostrar las transmisiones defectuosas
    public static void mostrarTransmisionesDefectuosas(JTextArea textArea) {
        String query = "SELECT VIN, idModelo, transmision, fechaIngreso, cliente, idCliente, proveedor, defectuosa " +
                       "FROM VehiculosConTransmisionesDefectuosas";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            textArea.setText("Vehículos con transmisiones defectuosas:\n");
            textArea.append(String.format("%-17s %-10s %-15s %-15s %-20s %-10s %-15s %-10s%n", 
                              "VIN", "Modelo", "Transmisión", "Fecha Ingreso", 
                              "Cliente", "ID Cliente", "Proveedor", "Defectuosa"));

            while (rs.next()) {
                textArea.append(String.format("%-17s %-10s %-15s %-15s %-20s %-10s %-15s %-10s%n",
                                  rs.getString("VIN"),
                                  rs.getString("idModelo"),
                                  rs.getString("transmision"),
                                  rs.getDate("fechaIngreso"),
                                  rs.getString("cliente"),
                                  rs.getString("idCliente"),
                                  rs.getString("proveedor"),
                                  rs.getString("defectuosa")));
            }
        } catch (SQLException e) {
            textArea.append("Error al mostrar las transmisiones defectuosas\n");
            e.printStackTrace();
        }
    }

    // Metodo para mostrar las dos mejores marcas por cantidad de dolares vendidos
    public static void mostrarTop2MarcasDolares(JTextArea textArea) {
        String query = "SELECT marca, total_ventas " +
                       "FROM Top2MarcasDolares " +
                       "ORDER BY total_ventas DESC";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            textArea.setText("Top 2 marcas por ventas en dólares (último año):\n");
            textArea.append(String.format("%-20s %-15s%n", "Marca", "Total Ventas"));

            while (rs.next()) {
                textArea.append(String.format("%-20s $%-15.2f%n",
                                  rs.getString("marca"),
                                  rs.getDouble("total_ventas")));
            }
        } catch (SQLException e) {
            textArea.append("Error al mostrar el Top 2 de marcas por ventas en dolares\n");
            e.printStackTrace();
        }
    }

    // Metodo para mostrar las dos mejores marcas por unidades vendidos
    public static void mostrarTop2MarcasUnidades(JTextArea textArea) {
        String query = "SELECT marca, total_unidades " +
                       "FROM Top2MarcasUnidades " +
                       "ORDER BY total_unidades DESC";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            textArea.setText("Top 2 marcas por unidades vendidas (último año):\n");
            textArea.append(String.format("%-20s %-10s%n", "Marca", "Total Unidades"));

            while (rs.next()) {
                textArea.append(String.format("%-20s %-10d%n",
                                  rs.getString("marca"),
                                  rs.getInt("total_unidades")));
            }
        } catch (SQLException e) {
            textArea.append("Error al mostrar el Top 2 de marcas por unidades vendidas\n");
            e.printStackTrace();
        }
    }

    // Metodo para mostrar el mes con mayores ventas de vehículos convertibles
    public static void mostrarMejorMesConvertibles(JTextArea textArea) {
        String query = "SELECT mes, total_convertibles " +
                       "FROM MejorMesConvertibles " +
                       "ORDER BY total_convertibles DESC";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            textArea.setText("Mes con mejores ventas de convertibles:\n");
            if (rs.next()) {
                textArea.append(String.format("Mes: %d, Ventas de Convertibles: %d%n",
                                  rs.getInt("mes"),
                                  rs.getInt("total_convertibles")));
            } else {
                textArea.append("No se encontraron ventas de convertibles\n");
            }
        } catch (SQLException e) {
            textArea.append("Error al mostrar el mejor mes para convertibles\n");
            e.printStackTrace();
        }
    }

    // Metodo para mostrar los distribuidores con el mayor tiempo promedio en inventario
    public static void mostrarDistribuidoresConMayorTiempoInventario(JTextArea textArea) {
        String query = "SELECT concesionario, tiempo_promedio_inventario " +
                       "FROM InventarioPromedio " +
                       "ORDER BY tiempo_promedio_inventario DESC";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            textArea.setText("Distribuidores con el mayor tiempo promedio en inventario:\n");
            textArea.append(String.format("%-20s %-10s%n", "Concesionario", "Tiempo Promedio (días)"));

            while (rs.next()) {
                textArea.append(String.format("%-20s %-10.2f%n",
                                  rs.getString("concesionario"),
                                  rs.getDouble("tiempo_promedio_inventario")));
            }
        } catch (SQLException e) {
            textArea.append("Error al mostrar los distribuidores con mayor tiempo promedio en inventario\n");
            e.printStackTrace();
        }
    }

    // Metodo para mostrar las busquedas de modelos de vehículos
    public static void mostrarBusquedasModelos(JTextArea textArea) {
        String query = "SELECT Usuario, Modelo_Buscado, Fecha_Busqueda " +
                       "FROM ADMIN.VISTABUSQUEDASMODELOS " + 
                       "ORDER BY Fecha_Busqueda DESC";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            textArea.setText("Búsquedas de modelos de vehículos:\n");
            textArea.append(String.format("%-20s %-20s %-15s%n", "Usuario", "Modelo Buscado", "Fecha Búsqueda"));

            while (rs.next()) {
                Date fecha = rs.getDate("Fecha_Busqueda");
                textArea.append(String.format("%-20s %-20s %-15s%n",
                              rs.getString("Usuario"),
                              rs.getString("Modelo_Buscado"),
                              fecha != null ? fecha.toString() : "No disponible"));
            }
        } catch (SQLException e) {
            textArea.append("Error al mostrar las busquedas de modelos\n");
            e.printStackTrace();
        }
    }

    // Menu principal del departamento de marketing 
    public static void menuMarketingGUI(String nombreUsuario, int idUsuario) {
        JFrame frame = new JFrame("Departamento de Marketing");
        frame.setSize(600, 400);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
        JLabel label = new JLabel("Bienvenido, " + nombreUsuario + ", al Departamento de Marketing");
        panel.add(label);
    
        JTextArea textArea = new JTextArea(10, 40); 
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);
    
        // Botones con sus respectivos ActionListeners
        JButton btnTendenciasVentas = new JButton("Mostrar Tendencias de Ventas");
        btnTendenciasVentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarTendenciasVentas(textArea);
            }
        });
    
        JButton btnTransmisionesDefectuosas = new JButton("Mostrar Transmisiones Defectuosas");
        btnTransmisionesDefectuosas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarTransmisionesDefectuosas(textArea);
            }
        });
    
        JButton btnTop2MarcasDolares = new JButton("Top 2 Marcas por Ventas (Dólares)");
        btnTop2MarcasDolares.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarTop2MarcasDolares(textArea);
            }
        });
    
        JButton btnTop2MarcasUnidades = new JButton("Top 2 Marcas por Unidades Vendidas");
        btnTop2MarcasUnidades.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarTop2MarcasUnidades(textArea);
            }
        });
    
        JButton btnMejorMesConvertibles = new JButton("Mejor Mes para Convertibles");
        btnMejorMesConvertibles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarMejorMesConvertibles(textArea);
            }
        });
    
        JButton btnDistribuidoresConTiempoInventario = new JButton("Distribuidores con Mayor Tiempo Inventario");
        btnDistribuidoresConTiempoInventario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarDistribuidoresConMayorTiempoInventario(textArea);
            }
        });
    
        JButton btnBusquedasModelos = new JButton("Mostrar Búsquedas de Modelos");
        btnBusquedasModelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarBusquedasModelos(textArea);
            }
        });
    
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    
        // Añadir todos los botones al panel
        panel.add(btnTendenciasVentas);
        panel.add(btnTransmisionesDefectuosas);
        panel.add(btnTop2MarcasDolares);
        panel.add(btnTop2MarcasUnidades);
        panel.add(btnMejorMesConvertibles);
        panel.add(btnDistribuidoresConTiempoInventario);
        panel.add(btnBusquedasModelos);
        panel.add(btnSalir);
    
        frame.add(panel);
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
    

}