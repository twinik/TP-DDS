package ar.edu.utn.frba.dds.models.domain.notifications;

import ar.edu.utn.frba.dds.models.domain.whatsappSender.WhatsappSenderAdapter;
import lombok.AllArgsConstructor;

/**
 * estrategia de notificacion mandando mensaje por whatapp
 */
@AllArgsConstructor
public class WhatsappSenderStrategy implements NotificationStrategy {

    public WhatsappSenderAdapter whatsappSenderAdapter;


    public WhatsappSenderStrategy() {
    }

    public void notificar(Contactable contactable, String asunto, String message) {
        whatsappSenderAdapter.enviarWhatsapp(message, contactable.telefonoCompleto());
    }

}