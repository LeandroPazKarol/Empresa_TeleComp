package Dao;

import Repository.INotificacion;
import entity.Notificacion;
import database.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

public class NotificacionDaoImpl implements INotificacion {

    @Override
    public void guardar(Notificacion n) {
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement ps = con.prepareStatement("CALL sp_guardar_notificacion(?, ?, ?, ?)");
            ps.setInt(1, n.getIdReclamo());
            ps.setString(2, n.getMedio());
            ps.setString(3, n.getContenido());
            Date fecha = n.getFechaEnvio() != null ? n.getFechaEnvio() : new Date();
            ps.setTimestamp(4, new Timestamp(fecha.getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
