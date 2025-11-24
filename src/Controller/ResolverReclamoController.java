package Controller;

import Service.ReclamoService;
import entity.Reclamo;
import formularios.ReclamoView;
import java.util.Date;
import java.util.List;

public class ResolverReclamoController {

    private ReclamoService service;
    private ReclamoView view;

    public ResolverReclamoController(ReclamoService service, ReclamoView view) {
        this.service = service;
        this.view = view;

        this.view.setResolverReclamoListener((id, descripcion, responsable) -> {
            try {
                // Llama al método para actualizar resolución (fecha se actualiza en BD automáticamente)
                service.actualizarResolucion(id, descripcion, responsable);

                List<Reclamo> reclamos = service.listarReclamosAbiertos();
                view.actualizarListaReclamos(reclamos);
            } catch (Exception e) {
                view.mostrarError(e.getMessage());
            }
        });

        // Cargar datos iniciales
        try {
            List<Reclamo> reclamos = service.listarReclamosAbiertos();
            view.actualizarListaReclamos(reclamos);
        } catch (Exception e) {
            view.mostrarError(e.getMessage());
        }
    }
}
