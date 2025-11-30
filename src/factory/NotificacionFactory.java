package factory;

import Repository.IClienteObservador;
import entity.Reclamo;
import observer.NotificadorEmail;
import observer.NotificadorSMS;

public class NotificacionFactory {

    public static IClienteObservador crear(String medio) {
        switch (medio.toUpperCase()) {
            case "SMS":
                return new NotificadorSMS();
            case "EMAIL":
                return new NotificadorEmail();
            case "AMBOS":
                return new IClienteObservador() {
                    @Override
                    public void notificar(Reclamo reclamo) {
                        new NotificadorSMS().notificar(reclamo);
                        new NotificadorEmail().notificar(reclamo);
                    }

            @Override
            public void notificarCambio(String mensaje) {
               
            }
                };

            default:
                throw new IllegalArgumentException("Medio no válido");
        }
    }
}
