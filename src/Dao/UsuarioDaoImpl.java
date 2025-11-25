package Dao;

import database.ConexionBD;
import entity.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public Usuario validarUsuario(String nombreUsuario, String contraseña) {
        String sql = "{CALL sp_validarUsuario(?,?)}";
        Usuario usuario = null;
        try (Connection con = ConexionBD.getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, nombreUsuario);
            cs.setString(2, contraseña);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                    usuario.setRol(rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al validar usuario: " + e.getMessage());
        }
        return usuario;
    }

    @Override
    public boolean registrar(Usuario u) {
        String sql = "{CALL sp_registrarUsuario(?,?,?,?)}";
        try (Connection con = ConexionBD.getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, u.getNombreUsuario());
            cs.setString(2, u.getPassword());
            cs.setString(3, u.getRol());
            cs.setInt(4, u.getIdArea());
            cs.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "{CALL sp_listarUsuarios()}";
        try (Connection con = ConexionBD.getConnection(); CallableStatement cs = con.prepareCall(sql); ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombreUsuario(rs.getString("nombreUsuario"));
                u.setRol(rs.getString("rol"));
                u.setNombreArea(rs.getString("nombreArea"));
                lista.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizar(Usuario u) {
        String sql = "{CALL sp_actualizarUsuario(?,?,?,?,?)}";
        try (Connection con = ConexionBD.getConnection(); CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, u.getIdUsuario());
            cs.setString(2, u.getNombreUsuario());
            cs.setString(3, u.getPassword());
            cs.setString(4, u.getRol());
            cs.setInt(5, u.getIdArea());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showConfirmDialog(null, "Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idUsuario) {
        String sql = "{CALL sp_eliminarUsuario(?)}";
        try (Connection con = ConexionBD.getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idUsuario);
            cs.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error al elimnar: " + e.getMessage());
            return false;
        }
    }

}
