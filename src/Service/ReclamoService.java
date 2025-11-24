package Service;

import Repository.IReclamo;
import entity.Reclamo;
import java.util.Date;
import java.util.List;

public class ReclamoService {

    private IReclamo ireclamo;

    public ReclamoService(IReclamo ireclamo) {
        this.ireclamo = ireclamo;
    }

    public void resolverReclamo(int id) throws Exception {
        ireclamo.resolverReclamo(id);
    }

    public List<Reclamo> listarReclamosAbiertos() throws Exception {
        return ireclamo.listarReclamosAbiertos();
    }

    public void resolverReclamoConResolucion(int idReclamo, Date fechaResolucion, String descripcion, String responsable) throws Exception {
        ireclamo.resolverReclamoConResolucion(idReclamo, fechaResolucion, descripcion, responsable);
    }

    public void actualizarResolucion(int idReclamo, String descripcion, String responsable) throws Exception {
        ireclamo.actualizarResolucion(idReclamo, descripcion, responsable);
    }

}
