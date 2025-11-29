package Dao;

import database.ConexionBD;
import entity.Reclamo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ReclamoDao {
    
    
    Statement stm = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

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
    
    
    public List<Reclamo> readAll() throws Exception {
        List<Reclamo> lista = new ArrayList<>();
        try {
            con = ConexionBD.getConnection();
            sql = "{call sp_consultarRegistros}";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            lista = cargaLista(rs);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            con.close();
        }
        return lista;
    }
    
    private List<Reclamo> cargaLista(ResultSet rs) throws SQLException {
        List<Reclamo> aux = new ArrayList<>();
        while (rs.next()) {
            Reclamo Reg = new Reclamo();
            Reg.setIdReclamo(rs.getInt(1));
            Reg.setFechaRegistro(rs.getDate(2));
            Reg.setTipo(rs.getString(3));
            Reg.setDescripcion(rs.getString(4));
            Reg.setEstado(rs.getString(5));
            Reg.setCanalIngreso(rs.getString(6));
            Reg.setIdCLiente(rs.getInt(7));
            Reg.setIdAreaAsignada(rs.getInt(8));
            Reg.setIdUsuarioRegistra(rs.getInt(9));
            aux.add(Reg);
        }
        rs.close();
        return aux;
    }
}
