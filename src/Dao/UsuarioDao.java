package Dao;

import database.ConexionBD;
import entity.Usuario;
import java.sql.*;
import javax.swing.JOptionPane;

public class UsuarioDao {

    private Connection con = ConexionBD.getConnection();

    public Usuario validarUsuario(String nombreUsuario, String contraseña) {
        String sql = "{CALL sp_validarUsuario(?,?)}";
        Usuario usuario = null;

        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, nombreUsuario);
            cs.setString(2, contraseña);

            try (ResultSet rs = cs.executeQuery()) {
                if(rs.next()){
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setRol(rs.getString("rol"));
            }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar usaurio: " + e.getMessage());
        }
        return usuario;
    }
}
