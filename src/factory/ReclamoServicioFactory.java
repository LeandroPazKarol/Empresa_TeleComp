package factory;

import entity.Reclamo;

public class ReclamoServicioFactory implements ReclamoFactory {
    @Override
    public Reclamo crearReclamo() {
        Reclamo r = new Reclamo();
        r.setTipo("Servicio");
        r.setEstado("Abierto");
        return r;
    }
}
