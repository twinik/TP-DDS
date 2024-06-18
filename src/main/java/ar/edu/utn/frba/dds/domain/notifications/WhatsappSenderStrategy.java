package ar.edu.utn.frba.dds.domain.notifications;

import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.domain.whatsappSender.WhatsappSenderAdapter;
import lombok.AllArgsConstructor;

/**
 * estrategia de notificacion mandando mensaje por whatapp
 */
@AllArgsConstructor
public class WhatsappSenderStrategy implements NotificationStrategy {

    public WhatsappSenderStrategy() {
    }


    public WhatsappSenderAdapter whatsappSenderAdapter;


    public void notificar(MedioDeContacto medioContacto, String message) {
        // TODO implement here
    }

}