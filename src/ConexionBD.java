import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConexionBD {

    private static final String HOST = "database-3.cknyyy64wdhq.us-east-1.rds.amazonaws.com"; //enlace a la bd
    private static final String PORT = "1521";
    private static final String SERVICE_NAME = "ORCL";
    private static final String URL = "jdbc:oracle:thin:@" + HOST + ":" + PORT + "/" + SERVICE_NAME;
    private static final String USER = "admin";  //usuario de la bd
    private static final String PASSWORD = "admin2024";
               //informar si se conecto a la bd
    public static Connection conectar() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("");
            return connection;
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            throw e;
        }
    }
}
