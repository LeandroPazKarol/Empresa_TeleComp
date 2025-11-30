package observer;

import Repository.IClienteObservador;
import Dao.NotificacionDaoImpl;
import entity.Notificacion;
import entity.Reclamo;

public class NotificadorSMS implements IClienteObservador {

    @Override
    public void notificar(Reclamo reclamo) {
        NotificacionDaoImpl dao = new NotificacionDaoImpl();
        dao.guardar(new Notificacion(reclamo.getIdReclamo(), "SMS", "Su reclamo ha sido resuelto."));
    }

    @Override
    public void notificarCambio(String mensaje) {
    }
}
