package Dao;


import Repository.IEncuesta;
import database.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import entity.EncuestaSatisfaccion;
import javax.swing.JOptionPane;

public class EncuestaDaoImpl implements IEncuesta {
    
    @Override
    public void guardar(EncuestaSatisfaccion e) {
        try (Connection con = ConexionBD.getConnection();
             CallableStatement cs = con.prepareCall("{call sp_guardar_encuesta(?,?,?)}")) {
            
            cs.setInt(1, e.getIdCliente());
            cs.setInt(2, e.getIdReclamo());
            cs.setDate(3, new java.sql.Date(e.getFechaEnvio().getTime()));
            cs.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error encuesta: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
