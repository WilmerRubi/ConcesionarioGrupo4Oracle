import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public User authenticate(String username, String password) {
        String query = "SELECT tipoUsuario, idUsuario FROM Usuario WHERE nombre = ? AND contrasena = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipoUsuario = rs.getString("tipoUsuario");
                    int idUsuario = rs.getInt("idUsuario");

                    User user = new User(username, password, tipoUsuario);
                    user.setId(idUsuario);

                    return user;
                } else {
                    return null; // Usuario no encontrado
                }
            }
        } catch (SQLException e) {
            System.out.println("Error de conexion a la base de datos");
            e.printStackTrace();
            return null;
        }
    }
}


