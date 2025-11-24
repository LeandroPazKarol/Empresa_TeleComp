package factory;

import entity.Reclamo;

public class ReclamoTecnicoFactory implements ReclamoFactory {
    @Override
    public Reclamo crearReclamo() {
        Reclamo r = new Reclamo();
        r.setTipo("Técnico");
        r.setEstado("Abierto");
        return r;
    }
}
