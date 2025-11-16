package entity;

import java.util.Date;

public class Resolucion {

    private int idResolucion;
    private Date fechaResolucion;
    private String descripcion;
    private String responsable;
    private int idReclamo;

    public Resolucion() {
    }

    public Resolucion(int idResolucion, Date fechaResolucion, String descripcion, String responsable, int idReclamo) {
        this.idResolucion = idResolucion;
        this.fechaResolucion = fechaResolucion;
        this.descripcion = descripcion;
        this.responsable = responsable;
        this.idReclamo = idReclamo;
    }

    public int getIdResolucion() {
        return idResolucion;
    }

    public void setIdResolucion(int idResolucion) {
        this.idResolucion = idResolucion;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public int getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(int idReclamo) {
        this.idReclamo = idReclamo;
    }

}
