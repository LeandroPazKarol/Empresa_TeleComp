package Dao;

import Repository.IReclamo;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import database.ConexionBD;
import entity.Reclamo;
import entity.Resolucion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResolverReclamoDao implements IReclamo {

    @Override
    public void resolverReclamo(int id) throws Exception {
        try (Connection cn = ConexionBD.getConnection(); CallableStatement cs = cn.prepareCall("{call sp_resolverReclamo(?)}")) {
            cs.setInt(1, id);
            cs.executeUpdate();
        }
    }

    @Override
    public void resolverReclamoConResolucion(int idReclamo, Date fechaResolucion, String descripcion, String responsable) throws Exception {
        try (Connection cn = ConexionBD.getConnection(); CallableStatement cs = cn.prepareCall("{call sp_resolverReclamoConResolucion(?,?,?,?)}")) {
            cs.setInt(1, idReclamo);
            cs.setDate(2, new java.sql.Date(fechaResolucion.getTime()));
            cs.setString(3, descripcion);
            cs.setString(4, responsable);
            cs.executeUpdate();
        }
    }

    @Override
    public List<Reclamo> listarReclamosAbiertos() throws Exception {
        List<Reclamo> lista = new ArrayList<>();
        try (Connection cn = ConexionBD.getConnection(); CallableStatement cs = cn.prepareCall("{call sp_listarTodosReclamosConResolucion()}"); ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Reclamo r = new Reclamo();
                r.setIdReclamo(rs.getInt("idReclamo"));
                r.setTipo(rs.getString("tipo"));
                r.setDescripcion(rs.getString("descripcion"));
                r.setEstado(rs.getString("estado"));
                r.setIdCLiente(rs.getInt("idCliente"));
                r.setIdAreaAsignada(rs.getInt("idAreaAsignada"));
                r.setIdUsuarioRegistra(rs.getInt("idUsuarioRegistra"));

                // Cargar datos de resolución si existen
                int idResolucion = rs.getInt("idResolucion");
                if (idResolucion > 0) {
                    Resolucion resolucion = new Resolucion();
                    resolucion.setIdResolucion(idResolucion);
                    resolucion.setFechaResolucion(rs.getDate("fechaResolucion"));
                    resolucion.setDescripcion(rs.getString("descripcionResolucion"));
                    resolucion.setResponsable(rs.getString("responsable"));
                    resolucion.setIdReclamo(r.getIdReclamo());
                    r.setResolucion(resolucion);
                }
                lista.add(r);
            }
        }
        return lista;
    }

    public void actualizarResolucion(int idReclamo, String descripcion, String responsable) throws Exception {
        try (Connection cn = ConexionBD.getConnection(); CallableStatement cs = cn.prepareCall("{call sp_actualizarResolucion(?,?,?)}")) {

            cs.setInt(1, idReclamo);
            cs.setString(2, descripcion);
            cs.setString(3, responsable);

            cs.executeUpdate();
        }
    }

}
