package Repository;

import entity.Reclamo;

/**
 * Patrón utilizado: Observer
 * Define el contrato de quienes
 * vigilan las acciones a Dao
 */

public interface IClienteObservador {
    void notificarCambio(String mensaje);
    void notificar(Reclamo reclamo);
}
