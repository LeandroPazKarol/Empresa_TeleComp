package builder;

import entity.Reclamo;
import java.util.Date;

public class ReclamoBuilder {

    private final Reclamo reclamo;

    public ReclamoBuilder(Reclamo reclamoBase) {
        this.reclamo = reclamoBase;
        this.reclamo.setFechaRegistro(new Date());
    }

    public ReclamoBuilder descripcion(String desc) {
        reclamo.setDescripcion(desc);
        return this;
    }

    public ReclamoBuilder canal(String canal) {
        reclamo.setCanalIngreso(canal);
        return this;
    }

    public ReclamoBuilder cliente(int idCliente) {
        reclamo.setIdCLiente(idCliente);
        return this;
    }

    public ReclamoBuilder area(int idArea) {
        reclamo.setIdAreaAsignada(idArea);
        return this;
    }

    public ReclamoBuilder usuario(int idUsuario) {
        reclamo.setIdUsuarioRegistra(idUsuario);
        return this;
    }

    public Reclamo build() {
        return reclamo;
    }
}
