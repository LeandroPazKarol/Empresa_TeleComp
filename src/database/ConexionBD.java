package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBD {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/telecom_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private ConexionBD() {
    }

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion a la base de datos: " + e.getMessage());
            System.exit(1);
            return null; // Nunca llegará aquí, solo para compilación.
        }
    }
}
