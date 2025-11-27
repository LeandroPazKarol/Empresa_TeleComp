package entity;

import java.util.Date;

public class DetalleReclamo {
    private int idReclamo;
    private Date fechaRegistro;
    private String tipo;
    private String estado;
    private Date fechaResolucion; // puede ser null
    private Double tiempoResolucionHoras; // puede ser null
    private boolean primerContacto;

    public DetalleReclamo() {}

    // getters y setters
    public int getIdReclamo() { return idReclamo; }
    public void setIdReclamo(int idReclamo) { this.idReclamo = idReclamo; }
    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Date getFechaResolucion() { return fechaResolucion; }
    public void setFechaResolucion(Date fechaResolucion) { this.fechaResolucion = fechaResolucion; }
    public Double getTiempoResolucionHoras() { return tiempoResolucionHoras; }
    public void setTiempoResolucionHoras(Double tiempoResolucionHoras) { this.tiempoResolucionHoras = tiempoResolucionHoras; }
    public boolean isPrimerContacto() { return primerContacto; }
    public void setPrimerContacto(boolean primerContacto) { this.primerContacto = primerContacto; }
}
