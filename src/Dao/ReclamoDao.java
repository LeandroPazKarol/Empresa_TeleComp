package Dao;

import database.ConexionBD;
import entity.Reclamo;
import java.sql.*;
import javax.swing.JOptionPane;

public class ReclamoDao {

    private Connection con = ConexionBD.getConnection();

    public boolean registrarReclamo(Reclamo r) {
        String sql = "{CALL sp_registrarReclamo(?,?,?,?,?,?,?)}";

        try (CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, r.getTipo());
            cs.setString(2, r.getDescripcion());
            cs.setString(3, r.getEstado());
            cs.setString(4, r.getCanalIngreso());
            cs.setInt(5, r.getIdCLiente());
            cs.setInt(6, r.getIdAreaAsignada());
            cs.setInt(7, r.getIdUsuarioRegistra());

            cs.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar reclamo: " + e.getMessage());
            return false;
        }
    }
}
