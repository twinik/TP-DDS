package ar.edu.utn.frba.dds.domain.notifications;

import ar.edu.utn.frba.dds.domain.telegramSender.TelegramSenderAdapter;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;

/**
 * Estrategia de notificacion por telegram
 */
public class TelegramNotificacionStrategy implements NotificationStrategy {

    private TelegramSenderAdapter telegramSenderAdapter;

    public void notificar(MedioDeContacto medioContacto) {
        // TODO implement here
    }


}