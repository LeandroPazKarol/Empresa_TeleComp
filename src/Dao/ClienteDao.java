package Dao;

import database.ConexionBD;
import entity.Cliente;
import java.sql.*;
import javax.swing.JOptionPane;

public class ClienteDao {

    private Connection con = ConexionBD.getConnection();

    public boolean registrarCliente(Cliente cliente) throws SQLException {
        String sql = "{CALL sp_registrarCliente(?,?,?,?,?,?)}";

        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, cliente.getDNI());
            cs.setString(2, cliente.getNombre());
            cs.setString(3, cliente.getApellidos());
            cs.setString(4, cliente.getTelefono());
            cs.setString(5, cliente.getEmail());
            cs.setString(5, cliente.getNumeroContrato());

            cs.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Eroor al registrar cliente: " + e.getMessage());
            return false;
        }
    }

    public Cliente buscarporDNI(String dni) throws SQLException {
        String sql = "{CALL sp_buscarClientePOrDNI(?)}";
        Cliente cliente = null;

        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, dni);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    cliente.setDNI(rs.getString("DNI"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellidos(rs.getString("apellidos"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setEmail(rs.getString("emial"));
                    cliente.setNumeroContrato(rs.getString("numeroCOntrato"));
                }
            }
        }catch(SQLException e){
        JOptionPane.showMessageDialog(null,"Error al buscar cliente: "+e.getMessage());
        }
   return cliente;
    }

    
    //agregar otros metodos
}
