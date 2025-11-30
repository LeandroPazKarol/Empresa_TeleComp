package Service;

import Dao.ResolverReclamoDao;
import Repository.IClienteObservador;
import entity.Reclamo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReclamoService {

    private ResolverReclamoDao dao;  
    private List<IClienteObservador> observadores;

    public ReclamoService(ResolverReclamoDao resolverReclamoDao) {
        this.dao = new ResolverReclamoDao();  
        this.observadores = new ArrayList<>();
    }

    // Agregar observador
    public void agregarObservador(IClienteObservador obs) {
        if (!observadores.contains(obs)) {
            observadores.add(obs);
        }
    }

    // Notificar a todos los observadores
    private void notificarObservadores(Reclamo reclamo) {
        for (IClienteObservador obs : observadores) {
            obs.notificar(reclamo);
        }
        observadores.clear(); 
    }

    // Listar reclamos abiertos
    public List<Reclamo> listarReclamosAbiertos() throws Exception {
        return dao.listarReclamosAbiertos();
    }

    // Resolver reclamo: actualizar resolución y cambiar estado
    public void resolverReclamo(Reclamo reclamo, String descripcion, String responsable) throws Exception {

        Date fecha = new Date();

        // Actualiza resolución y estado a RESUELTO en BD
        dao.resolverReclamoConResolucion(
                reclamo.getIdReclamo(),
                fecha,
                descripcion,
                responsable
        );
        reclamo.setEstado("RESUELTO");
        reclamo.setResolucion(new entity.Resolucion());
        reclamo.getResolucion().setDescripcion(descripcion);
        reclamo.getResolucion().setResponsable(responsable);
        reclamo.getResolucion().setFechaResolucion(fecha);

        // Notificar a todos los observadores (encuesta, SMS, email)
        notificarObservadores(reclamo);
    }
}
