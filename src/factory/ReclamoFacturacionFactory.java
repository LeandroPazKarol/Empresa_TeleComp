package factory;

import entity.Reclamo;

public class ReclamoFacturacionFactory implements ReclamoFactory {
    @Override
    public Reclamo crearReclamo() {
        Reclamo r = new Reclamo();
        r.setTipo("Facturación");
        r.setEstado("Abierto");
        return r;
    }
}
