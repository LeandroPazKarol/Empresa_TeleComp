package entity;

import java.util.Date;

public class Notificaciones {

    private int idNotificacion;
    private Date fechaEnvio;
    private String medio;
    private String contenido;
    private int idReclamo;

    public Notificaciones() {
    }

    public Notificaciones(int idNotificacion, Date fechaEnvio, String medio, String contenido, int idReclamo) {
        this.idNotificacion = idNotificacion;
        this.fechaEnvio = fechaEnvio;
        this.medio = medio;
        this.contenido = contenido;
        this.idReclamo = idReclamo;
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getMedio() {
        return medio;
    }

    public void setMedio(String medio) {
        this.medio = medio;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(int idReclamo) {
        this.idReclamo = idReclamo;
    }

}
