package entity;

import java.util.Date;

public class Notificacion {

    private int idNotificacion;
    private Date fechaEnvio;
    private String medio;
    private String contenido;
    private int idReclamo;

    public Notificacion() {
    }

    public Notificacion(int idReclamo, String medio, String contenido) {
        this.idReclamo = idReclamo;
        this.medio = medio;
        this.contenido = contenido;
        this.fechaEnvio = new Date(); // Se pone la fecha actual automáticamente
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
