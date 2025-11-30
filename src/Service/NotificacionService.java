
package Service;

import Repository.IClienteObservador;
import entity.Reclamo;
import java.util.ArrayList;
import java.util.List;


public class NotificacionService {

    private List<IClienteObservador> observadores = new ArrayList<>();

    // Método para agregar observadores (como encuestas, notificaciones por email o sms.)
    public void agregarObservador(IClienteObservador observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    // Método para notificar a todos los observadores cuando el reclamo está resuelto
    public void notificarReclamoResuelto(Reclamo reclamo) {
        for (IClienteObservador observador : observadores) {
            observador.notificar(reclamo);
        }
        observadores.clear(); // Limpiar la lista de observadores después de la notificación
    }
}
