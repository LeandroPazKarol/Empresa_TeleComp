package entity;
import entity.Resolucion;
import java.util.Date;

public class Reclamo {

    private int idReclamo;
    private Date fechaRegistro;
    private String tipo;
    private String descripcion;
    private String estado;
    private String canalIngreso;
    private int idCLiente;
    private int idAreaAsignada;
    private int idUsuarioRegistra;

    // Nueva asociación con Resolucion
    private Resolucion resolucion;

    public Reclamo() {
    }

    public Reclamo(int idReclamo, Date fechaRegistro, String tipo, String descripcion, String estado,
                   String canalIngreso, int idCLiente, int idAreaAsignada, int idUsuarioRegistra) {
        this.idReclamo = idReclamo;
        this.fechaRegistro = fechaRegistro;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.canalIngreso = canalIngreso;
        this.idCLiente = idCLiente;
        this.idAreaAsignada = idAreaAsignada;
        this.idUsuarioRegistra = idUsuarioRegistra;
    }

    // Getters y setters para todos los atributos

    public int getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(int idReclamo) {
        this.idReclamo = idReclamo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCanalIngreso() {
        return canalIngreso;
    }

    public void setCanalIngreso(String canalIngreso) {
        this.canalIngreso = canalIngreso;
    }

    public int getIdCLiente() {
        return idCLiente;
    }

    public void setIdCLiente(int idCLiente) {
        this.idCLiente = idCLiente;
    }

    public int getIdAreaAsignada() {
        return idAreaAsignada;
    }

    public void setIdAreaAsignada(int idAreaAsignada) {
        this.idAreaAsignada = idAreaAsignada;
    }

    public int getIdUsuarioRegistra() {
        return idUsuarioRegistra;
    }

    public void setIdUsuarioRegistra(int idUsuarioRegistra) {
        this.idUsuarioRegistra = idUsuarioRegistra;
    }

    public Resolucion getResolucion() {
        return resolucion;
    }

    public void setResolucion(Resolucion resolucion) {
        this.resolucion = resolucion;
    }
}
