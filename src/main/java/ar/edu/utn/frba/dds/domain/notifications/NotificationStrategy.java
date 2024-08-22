package ar.edu.utn.frba.dds.domain.notifications;

import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;

/**
 *  Estrategia para notificar segun el medio de contacto elegido
 */
public interface NotificationStrategy {

    void notificar(Contactable contactable, String message);

}