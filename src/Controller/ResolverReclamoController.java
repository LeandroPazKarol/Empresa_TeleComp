package Controller;

import Service.ReclamoService;
import entity.Reclamo;
import factory.NotificacionFactory;
import formularios.ReclamoView;
import observer.EnviadorEncuesta;

public class ResolverReclamoController {

    private ReclamoService service;
    private ReclamoView view;

    public ResolverReclamoController(ReclamoService service, ReclamoView view) {
        this.service = service;
        this.view = view;

        view.setResolverReclamoListener((id, descripcion, responsable, medio) -> {
            try {
                Reclamo reclamoSeleccionado = view.getReclamoSeleccionado();

                if (reclamoSeleccionado == null) {
                    view.mostrarError("Seleccione un reclamo para resolver.");
                    return;
                }

                // Validar campos obligatorios
                if (descripcion == null || descripcion.trim().isEmpty()) {
                    view.mostrarError("Debe ingresar una descripción de resolución.");
                    return;
                }
                if (responsable == null || responsable.trim().isEmpty()) {
                    view.mostrarError("Debe ingresar un responsable.");
                    return;
                }

                // Notificación según medio seleccionado
                if (!medio.equals("NINGUNO")) {
                    service.agregarObservador(NotificacionFactory.crear(medio));
                }

                // Siempre enviar encuesta (obligatorio)
                service.agregarObservador(new EnviadorEncuesta());

                // Resolver reclamo y actualizar estado a RESUELTO
                service.resolverReclamo(reclamoSeleccionado, descripcion.trim(), responsable.trim());

                // Actualizar lista de reclamos en la vista
                view.actualizarListaReclamos(service.listarReclamosAbiertos());

            } catch (Exception e) {
                view.mostrarError(e.getMessage());
            }
        });

        // Inicializar la tabla con reclamos abiertos
        try {
            view.actualizarListaReclamos(service.listarReclamosAbiertos());
        } catch (Exception e) {
            view.mostrarError(e.getMessage());
        }
    }
}
