package entity;

import java.util.Date;

public class EncuestaSatisfaccion {

    private int idEncuesta;
    private Date fechaEnvio;
    private int puntaje;
    private String comentario;
    private int idCliente;
    private int idReclamo;

    public EncuestaSatisfaccion() {
    }

    public EncuestaSatisfaccion(int idEncuesta, Date fechaEnvio, int puntaje, String comentario, int idCliente, int idReclamo) {
        this.idEncuesta = idEncuesta;
        this.fechaEnvio = fechaEnvio;
        this.puntaje = puntaje;
        this.comentario = comentario;
        this.idCliente = idCliente;
        this.idReclamo = idReclamo;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(int idReclamo) {
        this.idReclamo = idReclamo;
    }

}
