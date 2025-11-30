
package observer;

import Repository.IClienteObservador;
import Dao.EncuestaDaoImpl;
import entity.EncuestaSatisfaccion;
import entity.Reclamo;

public class EnviadorEncuesta implements IClienteObservador {

    @Override
    public void notificar(Reclamo reclamo) {
        EncuestaDaoImpl dao = new EncuestaDaoImpl();
        dao.guardar(new EncuestaSatisfaccion(reclamo.getIdCLiente(), reclamo.getIdReclamo()));
    }

    @Override
    public void notificarCambio(String mensaje) {

    }
}
