import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Clase principal para la aplicación de Login
public class ImprovedLoginApp {
    private UserService userService;

    public ImprovedLoginApp() {
        userService = new UserService();
        initUI();
    }

    private void initUI() {
        // Crear ventana principal
        JFrame frame = new JFrame("Login System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // Panel principal para los componentes
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder("Login to your account"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(lblUsername, gbc);

        JTextField txtUsername = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(lblPassword, gbc);

        JPasswordField txtPassword = new JPasswordField(20);
        gbc.gridx = 1;
        mainPanel.add(txtPassword, gbc);

        // Botón de Login
        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(70, 130, 180));
        btnLogin.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(btnLogin, gbc);

        frame.add(mainPanel);

        // Acción del botón Login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                String password = new String(txtPassword.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                User user = userService.authenticate(username, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(frame, "Welcome, " + user.getUsername() + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    openDashboard(user.getTipoUsuario(), user.getUsername(), user.getIdUsuario());
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Configurar y mostrar la ventana
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

// Método para una ventana basada en rol
private void openDashboard(String role, String username, int idUsuario) {
    if ("admin".equalsIgnoreCase(role)) {
        new InterfazAdministrador(); // Abre la interfaz para el admin
    } else if ("cliente".equalsIgnoreCase(role)) {
        InterfazCliente.menuClienteGUI(username, idUsuario); // Abre la interfaz para el cliente
    } else if ("marketing".equalsIgnoreCase(role)) {
        InterfazMarketing.menuMarketingGUI(username, idUsuario); // Abre la interfaz para marketing
    } else {
        JFrame dashboard = new JFrame("Dashboard - " + role);
        dashboard.setSize(500, 300);
        JLabel label = new JLabel("Bienvenido al dashboard de " + role + "!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        dashboard.add(label);
        dashboard.setLocationRelativeTo(null);
        dashboard.setVisible(true);
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImprovedLoginApp::new);
    }
}
