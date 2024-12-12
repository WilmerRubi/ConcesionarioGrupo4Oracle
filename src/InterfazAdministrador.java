import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;


public class InterfazAdministrador {

    public InterfazAdministrador() {
        crearInterfaz();
    }

    private void crearInterfaz() { 
        JFrame adminDashboard = new JFrame("Dashboard del Administrador");
        adminDashboard.setSize(600, 400);
        adminDashboard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminDashboard.setLayout(new GridBagLayout());
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        // Boton para modificar cliente
        JButton btnModificarCliente = new JButton("Modificar Cliente");
        btnModificarCliente.addActionListener(e -> modificarCliente());
        gbc.gridx = 1;
        adminDashboard.add(btnModificarCliente, gbc);
    
        // Boton para eliminar cliente
        JButton btnEliminarCliente = new JButton("Eliminar Cliente");
        btnEliminarCliente.addActionListener(e -> eliminarCliente());
        gbc.gridx = 2; 
        adminDashboard.add(btnEliminarCliente, gbc);
    
        // Boton para insertar cliente
        JButton btnInsertarCliente = new JButton("Insertar Cliente");
        btnInsertarCliente.addActionListener(e -> insertarCliente());
        gbc.gridx = 2; 
        gbc.gridx = 0;
        adminDashboard.add(btnInsertarCliente, gbc);
    
        // Boton para modificar modelo
        JButton btnModificarModelo = new JButton("Modificar Modelo");
        btnModificarModelo.addActionListener(e -> modificarModelo());
        gbc.gridx = 1; 
        adminDashboard.add(btnModificarModelo, gbc);
    
        // Boton para eliminar modelo
        JButton btnEliminarModelo = new JButton("Eliminar Modelo");
        btnEliminarModelo.addActionListener(e -> eliminarModelo());
        gbc.gridx = 2; 
        adminDashboard.add(btnEliminarModelo, gbc);
    
        // Boton para insertar modelo
        JButton btnInsertarModelo = new JButton("Insertar Modelo");
        btnInsertarModelo.addActionListener(e -> insertarModelo());
        gbc.gridx = 2; 
        gbc.gridx = 0;
        adminDashboard.add(btnInsertarModelo, gbc);
    
        // Boton para insertar usuario
        JButton btnInsertarUsuario = new JButton("Insertar Usuario");
        btnInsertarUsuario.addActionListener(e -> insertarUsuario());
        gbc.gridy = 2; 
        gbc.gridx = 0; 
        adminDashboard.add(btnInsertarUsuario, gbc);
    
        // Boton para modificar usuario
        JButton btnModificarUsuario = new JButton("Modificar Usuario");
        btnModificarUsuario.addActionListener(e -> modificarUsuario());
        gbc.gridx = 1;
        adminDashboard.add(btnModificarUsuario, gbc);
    
        // Boton para eliminar usuario
        JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
        btnEliminarUsuario.addActionListener(e -> eliminarUsuario());
        gbc.gridx = 2; 
        adminDashboard.add(btnEliminarUsuario, gbc);
    
        // Boton para insertar concesionario
        JButton btnInsertarConcesionario = new JButton("Insertar Concesionario");
        btnInsertarConcesionario.addActionListener(e -> insertarConcesionario());
        gbc.gridy = 3;
        gbc.gridx = 0; 
        adminDashboard.add(btnInsertarConcesionario, gbc);
    
        // Boton para insertar proveedor
        JButton btnInsertarProveedor = new JButton("Insertar Proveedor");
        btnInsertarProveedor.addActionListener(e -> insertarProveedor());
        gbc.gridy = 4;
        gbc.gridx = 0; 
        adminDashboard.add(btnInsertarProveedor, gbc);
    
        // Boton para insertar planta
        JButton btnInsertarPlanta = new JButton("Insertar Planta");
        btnInsertarPlanta.addActionListener(e -> insertarPlanta());
        gbc.gridy = 5;
        gbc.gridx = 0;  
        adminDashboard.add(btnInsertarPlanta, gbc);
    
        // Boton para ver la bitácora
        JButton btnVerBitacora = new JButton("Ver Bitácora");
        btnVerBitacora.addActionListener(e -> verBitacora());
        gbc.gridy = 6; 
        gbc.gridx = 0; 
        adminDashboard.add(btnVerBitacora, gbc);
    
        // Boton para salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> System.exit(0));  
        gbc.gridy = 7;
        gbc.gridx = 0;
        adminDashboard.add(btnSalir, gbc);
    
        adminDashboard.setLocationRelativeTo(null);
        adminDashboard.setVisible(true);
    }
    
    
    

    // Método para insertar un cliente en la BD
    public static void insertarCliente() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String direccion = JOptionPane.showInputDialog("Ingrese la dirección del cliente:");
        String noTelefonoStr = JOptionPane.showInputDialog("Ingrese el número de teléfono del cliente:");
        String sexo = JOptionPane.showInputDialog("Ingrese el sexo del cliente:");
        String ingresosAnualesStr = JOptionPane.showInputDialog("Ingrese los ingresos anuales del cliente:");
        String idUsuarioStr = JOptionPane.showInputDialog("Ingrese el ID del usuario asignado al cliente:");

        try {
            int noTelefono = Integer.parseInt(noTelefonoStr);
            double ingresosAnuales = Double.parseDouble(ingresosAnualesStr);
            int idUsuario = Integer.parseInt(idUsuarioStr);

            try (Connection conn = ConexionBD.conectar()) {
                CallableStatement cs = conn.prepareCall("{CALL InsertarClienteConUsuario(?, ?, ?, ?, ?, ?)}");
                cs.setString(1, nombre);
                cs.setString(2, direccion);
                cs.setInt(3, noTelefono);
                cs.setString(4, sexo);
                cs.setDouble(5, ingresosAnuales);
                cs.setInt(6, idUsuario);
                cs.execute();
                JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en la conversión de datos. Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void insertarModelo() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del modelo:");
        String estiloCarroceria = JOptionPane.showInputDialog("Ingrese el estilo de carrocería:");
        String marca = JOptionPane.showInputDialog("Ingrese la marca del modelo:");
    
        try (Connection conn = ConexionBD.conectar()) {
    
            CallableStatement cs = conn.prepareCall("{CALL InsertarModelo(?, ?, ?)}");
            cs.setString(1, nombre);
            cs.setString(2, estiloCarroceria);
            cs.setString(3, marca);
    
            cs.execute();
            JOptionPane.showMessageDialog(null, "Modelo registrado correctamente.");
        } catch (SQLException e) {
    
            if (e.getMessage().contains("ORA-20001")) {
                JOptionPane.showMessageDialog(null, "El modelo ya existe. Verifique los datos.", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al registrar el modelo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    // Método para insertar una planta en la BD
    public static void insertarPlanta() {

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la planta:");
        String ubicacion = JOptionPane.showInputDialog("Ingrese la ubicación de la planta:");

      
        if (nombre == null || nombre.trim().isEmpty() || ubicacion == null || ubicacion.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    
        try (Connection conn = ConexionBD.conectar()) {
       
            CallableStatement cs = conn.prepareCall("{CALL InsertarPlanta(?, ?)}");
            
        
            cs.setString(1, nombre); 
            cs.setString(2, ubicacion);  
     
            cs.execute();

      
            JOptionPane.showMessageDialog(null, "Planta registrada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
    
            JOptionPane.showMessageDialog(null, "Error al registrar la planta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void insertarConcesionario() {
   
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del concesionario:");
        String direccion = JOptionPane.showInputDialog("Ingrese la dirección del concesionario:");
        String telefonoStr = JOptionPane.showInputDialog("Ingrese el número de teléfono del concesionario:");
    
  
        if (nombre == null || nombre.trim().isEmpty() || direccion == null || direccion.trim().isEmpty() || telefonoStr == null || telefonoStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
    
        long telefono = -1;
        try {
            telefono = Long.parseLong(telefonoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El número de teléfono no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
      
        try (Connection conn = ConexionBD.conectar()) {
   
            CallableStatement cs = conn.prepareCall("{CALL InsertarConcesionario(?, ?, ?)}");
    
            
            cs.setString(1, nombre);  
            cs.setString(2, direccion);  
            cs.setLong(3, telefono);  
    
        
            cs.execute();
    
           
            JOptionPane.showMessageDialog(null, "Concesionario registrado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
     
            JOptionPane.showMessageDialog(null, "Error al registrar el concesionario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Método para insertar un proveedor en la BD
public static void insertarProveedor() {

    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del proveedor:");
    String direccion = JOptionPane.showInputDialog("Ingrese la dirección del proveedor:");
    String telefonoStr = JOptionPane.showInputDialog("Ingrese el número de teléfono del proveedor:");

    if (nombre == null || nombre.trim().isEmpty() || direccion == null || direccion.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Los campos nombre y dirección no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    long noTelefono = 0;
    try {
        noTelefono = Long.parseLong(telefonoStr); 
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El número de teléfono debe ser válido.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try (Connection conn = ConexionBD.conectar()) {
        CallableStatement cs = conn.prepareCall("{CALL InsertarProveedor(?, ?, ?)}");
        
        cs.setString(1, nombre);  
        cs.setString(2, direccion);  
        cs.setLong(3, noTelefono);  
        
      
        cs.execute();
        JOptionPane.showMessageDialog(null, "Proveedor registrado correctamente.");
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar el proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    private void verBitacora() {
    
        JFrame bitacoraFrame = new JFrame("Bitácora de acciones");
        bitacoraFrame.setSize(600, 400);
        bitacoraFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel bitacoraPanel = new JPanel();
        bitacoraPanel.setLayout(new BorderLayout());

        String[] columnas = {"ID Registro", "Acción", "Tabla", "Registro", "Fecha"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tablaBitacora = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaBitacora);
        bitacoraPanel.add(scrollPane, BorderLayout.CENTER);
    

        try (Connection conn = ConexionBD.conectar()) {
            String query = "SELECT * FROM ADMIN.BITACORA";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int idRegistro = rs.getInt("IDREGISTRO");
                        String accion = rs.getString("ACCION");
                        String tabla = rs.getString("TABLA");
                        String registro = rs.getString("REGISTRO");
                        Timestamp fecha = rs.getTimestamp("FECHA");
    
                        modelo.addRow(new Object[]{idRegistro, accion, tabla, registro, fecha});
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bitacoraFrame.add(bitacoraPanel);
        bitacoraFrame.setLocationRelativeTo(null);
        bitacoraFrame.setVisible(true);
    }
    
    // Método para insertar un usuario en la BD
public static void insertarUsuario() {
    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
    String usuario = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
    String contrasena = JOptionPane.showInputDialog("Ingrese la contraseña:");
    String email = JOptionPane.showInputDialog("Ingrese el correo electrónico del usuario:");

    try {
        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL InsertarUsuario(?, ?, ?, ?)}");
            cs.setString(1, nombre);
            cs.setString(2, usuario);
            cs.setString(3, contrasena);
            cs.setString(4, email);
            cs.execute();
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
public static void modificarModelo() {
    String idModeloStr = JOptionPane.showInputDialog("Ingrese el ID del modelo a modificar:");
    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del modelo:");

    try {
        int idModelo = Integer.parseInt(idModeloStr);

        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL ActualizarNombreModelo(?, ?)}");
            cs.setInt(1, idModelo);  
            cs.setString(2, nuevoNombre);  
            cs.execute();  

            JOptionPane.showMessageDialog(null, "Modelo actualizado correctamente.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El ID del modelo debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al actualizar el modelo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public static void eliminarModelo() {
    String idModeloStr = JOptionPane.showInputDialog("Ingrese el ID del modelo a eliminar:");

    try {
        int idModelo = Integer.parseInt(idModeloStr);

        try (Connection conn = ConexionBD.conectar()) {

            CallableStatement cs = conn.prepareCall("{CALL EliminarModelo(?)}");
            cs.setInt(1, idModelo);  
            cs.execute();  
            JOptionPane.showMessageDialog(null, "Modelo eliminado correctamente.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El ID del modelo debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al eliminar el modelo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
public static void modificarCliente() { 
    String idClienteStr = JOptionPane.showInputDialog("Ingrese el ID del cliente a modificar:");
    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del cliente:");
    String nuevaDireccion = JOptionPane.showInputDialog("Ingrese la nueva dirección del cliente:");
    String nuevoTelefonoStr = JOptionPane.showInputDialog("Ingrese el nuevo número de teléfono del cliente:");
    String nuevoSexo = JOptionPane.showInputDialog("Ingrese el nuevo sexo del cliente:");
    String nuevosIngresosStr = JOptionPane.showInputDialog("Ingrese los nuevos ingresos anuales del cliente:");

    try {
        int idCliente = Integer.parseInt(idClienteStr);
        int nuevoTelefono = Integer.parseInt(nuevoTelefonoStr);
        double nuevosIngresos = Double.parseDouble(nuevosIngresosStr);

        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL modificar_cliente(?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, idCliente);
            cs.setString(2, nuevoNombre);
            cs.setString(3, nuevaDireccion);
            cs.setInt(4, nuevoTelefono);
            cs.setString(5, nuevoSexo);
            cs.setDouble(6, nuevosIngresos);
            cs.execute();
            JOptionPane.showMessageDialog(null, "Cliente modificado correctamente.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error en la conversión de datos. Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al modificar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public static void eliminarCliente() {
    String idClienteStr = JOptionPane.showInputDialog("Ingrese el ID del cliente a eliminar:");

    try {
        int idCliente = Integer.parseInt(idClienteStr);

        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL eliminar_cliente(?)}");
            cs.setInt(1, idCliente);
            cs.execute();
            JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error en la conversión de datos. Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public static void modificarUsuario() {
    String idUsuarioStr = JOptionPane.showInputDialog("Ingrese el ID del usuario a modificar:");
    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del usuario:");
    String nuevaContrasena = JOptionPane.showInputDialog("Ingrese la nueva contraseña:");
    String nuevoTipoUsuario = JOptionPane.showInputDialog("Ingrese el nuevo tipo de usuario:");

    try {
        int idUsuario = Integer.parseInt(idUsuarioStr);
        try (Connection conn = ConexionBD.conectar()) {
            CallableStatement cs = conn.prepareCall("{CALL modificar_usuario(?, ?, ?, ?)}");

            cs.setInt(1, idUsuario);
            cs.setString(2, nuevoNombre);
            cs.setString(3, nuevaContrasena);
            cs.setString(4, nuevoTipoUsuario);
            
            cs.execute();
            
       
            JOptionPane.showMessageDialog(null, "Usuario modificado correctamente.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error en la conversión de datos. Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al modificar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public static void eliminarUsuario() {
    String idUsuarioStr = JOptionPane.showInputDialog("Ingrese el ID del usuario a eliminar:");

    try {
        int idUsuario = Integer.parseInt(idUsuarioStr);

        try (Connection conn = ConexionBD.conectar()) {
  
            CallableStatement cs = conn.prepareCall("{CALL eliminar_usuario(?)}");
            
      
            cs.setInt(1, idUsuario);
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error en la conversión de datos. Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}




    public static void main(String[] args) {
        new InterfazAdministrador();
    }
}