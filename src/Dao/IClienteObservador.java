package Dao;

/**
 * Patrón utilizado: Observer
 * Define el contrato de quienes
 * vigilan las acciones a Dao
 */

public interface IClienteObservador {
    void notificarCambio(String mensaje);
}
