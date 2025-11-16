
package database;

import java.sql.*;
import javax.swing.JOptionPane;

public class PruebaConexion {
    public static void main(String[] args) {
        Connection con=ConexionBD.getConnection();
    
        if(con !=null){
            JOptionPane.showMessageDialog(null, "Conexion exitosa", "Prueba de conexion",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Conexion a la base de datos");
        
    }else{
        JOptionPane.showMessageDialog(null, "Conexion Fallida", "Prueba de conexion", JOptionPane.ERROR_MESSAGE);
            System.out.println("Conexion fallida");
        }
}
}
