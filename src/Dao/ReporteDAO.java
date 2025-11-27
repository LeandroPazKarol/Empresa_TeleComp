package Dao;

import InterfaceDAO.IReporteDAO;
import database.ConexionBD;
import entity.DetalleReclamo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReporteDAO implements IReporteDAO {

    @Override
    public int obtenerTotalReclamos() throws SQLException {
        String sql = "{CALL sp_total_reclamos()}";
        try (Connection con = ConexionBD.getConnection();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    @Override
    public double obtenerPromedioHorasResolucion() throws SQLException {
        String sql = "{CALL sp_promedio_tiempo_resolucion()}";
        try (Connection con = ConexionBD.getConnection();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("horasPromedio");
            }
        }
        return 0;
    }

    @Override
    public double obtenerPorcentajePrimerContacto() throws SQLException {
        String sql = "{CALL sp_porcentaje_primer_contacto()}";
        try (Connection con = ConexionBD.getConnection();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("porcentaje");
            }
        }
        return 0;
    }

    /**
     * Lista los detalles del reporte. Si p_fechaDesde o p_fechaHasta son null, envía NULL en el call.
     * @param fechaDesde
     * @param fechaHasta
     * @return 
     * @throws java.sql.SQLException
     */
    @Override
    public List<DetalleReclamo> listarDetalleReporte(Timestamp fechaDesde, Timestamp fechaHasta) throws SQLException {
        List<DetalleReclamo> lista = new ArrayList<>();
        String sql = "{CALL sp_reporte_detalle(?, ?)}";
        try (Connection con = ConexionBD.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            if (fechaDesde != null) cs.setTimestamp(1, fechaDesde);
            else cs.setNull(1, Types.TIMESTAMP);

            if (fechaHasta != null) cs.setTimestamp(2, fechaHasta);
            else cs.setNull(2, Types.TIMESTAMP);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    DetalleReclamo d = new DetalleReclamo();
                    d.setIdReclamo(rs.getInt("idReclamo"));
                    Timestamp tsRegistro = rs.getTimestamp("fechaRegistro");
                    if (tsRegistro != null) d.setFechaRegistro(new Date(tsRegistro.getTime()));
                    d.setTipo(rs.getString("tipo"));
                    d.setEstado(rs.getString("estado"));
                    Timestamp tsRes = rs.getTimestamp("fechaResolucion");
                    if (tsRes != null) d.setFechaResolucion(new Date(tsRes.getTime()));
                    // tiempoResolucionHoras puede venir como null en SQL
                    double tiempo = rs.getDouble("tiempoResolucionHoras");
                    if (rs.wasNull()) d.setTiempoResolucionHoras(null);
                    else d.setTiempoResolucionHoras(tiempo);
                    int pc = rs.getInt("esPrimerContacto");
                    d.setPrimerContacto(pc == 1);
                    lista.add(d);
                }
            }
        }
        return lista;
    }
}
