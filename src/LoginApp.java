import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginApp {
    private UserService userService;

    public LoginApp() {
        userService = new UserService();
        initUI();
    }

    private void initUI() {
        // Crear ventana principal
        JFrame frame = new JFrame("Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel para inputs
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        // Agregar componentes al panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Espaciador
        panel.add(loginButton);

        // Acción del botón Login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                User user = userService.authenticate(username, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(frame,
                            "Welcome, " + user.getUsername() + "!\nRole: " + user.getTipoUsuario(),
                            "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    openDashboard(user.getTipoUsuario());
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Invalid username or password!",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Agregar panel al frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Método para abrir un dashboard basado en el rol
    private void openDashboard(String role) {
        JFrame dashboard = new JFrame("Dashboard - " + role);
        dashboard.setSize(400, 200);
        JLabel label = new JLabel("You are logged in as: " + role, SwingConstants.CENTER);
        dashboard.add(label);
        dashboard.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginApp::new);
    }
}
