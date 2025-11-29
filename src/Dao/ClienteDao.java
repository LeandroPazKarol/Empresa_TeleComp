package Dao;

import database.ConexionBD;
import entity.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDao {

    /**
     * Patrón utilizado: Singleton
     * Ya que garantiza una sola conexión
     * lógica y centraliza las notificaciones
     */
    private static ClienteDao instancia;

    // Constructor privado
    private ClienteDao() {
        observadores = new ArrayList<>();
    }

    public static synchronized ClienteDao getInstance() {
        if (instancia == null) {
            instancia = new ClienteDao();
        }
        return instancia;
    }

    // Patrón Observer siendo utilizado
    private List<IClienteObservador> observadores;

    public void agregarObservador(IClienteObservador obs) {
        observadores.add(obs);
    }

    private void notificarTodos(String mensaje) {
        for (IClienteObservador obs : observadores) {
            obs.notificarCambio(mensaje);
        }
    }

    // MÉTODOS CRUD
    public boolean registrarCliente(Cliente cliente) throws SQLException {
        String sql = "{CALL sp_registrarCliente(?,?,?,?,?,?)}";

        try (Connection con = ConexionBD.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, cliente.getDNI());
            cs.setString(2, cliente.getNombres()); // Usamos getNombres()
            cs.setString(3, cliente.getApellidos());
            cs.setString(4, cliente.getTelefono());
            cs.setString(5, cliente.getEmail());
            cs.setString(6, cliente.getNumeroContrato());

            cs.executeUpdate();
            
            notificarTodos("Nuevo cliente registrado: " + cliente.getDNI());
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarCliente(Cliente cliente) throws SQLException {
        String sql = "{CALL sp_actualizarCliente(?,?,?,?,?,?)}";

        try (Connection con = ConexionBD.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, cliente.getDNI());
            cs.setString(2, cliente.getNombres());
            cs.setString(3, cliente.getApellidos());
            cs.setString(4, cliente.getTelefono());
            cs.setString(5, cliente.getEmail());
            cs.setString(6, cliente.getNumeroContrato());

            cs.executeUpdate();
            
            notificarTodos("Cliente actualizado: " + cliente.getDNI());
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCliente(String dni) throws SQLException {
        String sql = "{CALL sp_eliminarCliente(?)}";

        try (Connection con = ConexionBD.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, dni);
            int filas = cs.executeUpdate();

            if (filas > 0) {
                notificarTodos("Cliente eliminado DNI: " + dni);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public Cliente buscarporDNI(String dni) throws SQLException {
        String sql = "{CALL sp_buscarClientePorDNI(?)}";
        Cliente cliente = null;

        try (Connection con = ConexionBD.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, dni);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    cliente = mapResultSetToCliente(rs);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar: " + e.getMessage());
        }
        return cliente;
    }

    public List<Cliente> listarClientes() throws SQLException {
        String sql = "{CALL sp_listarClientes()}";
        List<Cliente> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                lista.add(mapResultSetToCliente(rs));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Líneas de código que nos ahorra líneas de
     * código repetidas dentro de cada método CRUD
     */
    private Cliente mapResultSetToCliente(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setIdCliente(rs.getInt("idCliente"));
        c.setDNI(rs.getString("DNI"));
        c.setNombres(rs.getString("nombres")); 
        c.setApellidos(rs.getString("apellidos"));
        c.setTelefono(rs.getString("telefono"));
        c.setEmail(rs.getString("email"));
        c.setNumeroContrato(rs.getString("numeroContrato"));
        return c;
    }
}
