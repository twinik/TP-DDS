package ar.edu.utn.frba.dds.domain.notifications;

import ar.edu.utn.frba.dds.domain.telegramSender.TelegramSenderAdapter;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import lombok.AllArgsConstructor;

/**
 * Estrategia de notificacion por telegram
 */
@AllArgsConstructor
public class TelegramNotificacionStrategy implements NotificationStrategy {

    private TelegramSenderAdapter telegramSenderAdapter;

    public void notificar(MedioDeContacto medioContacto, String message) {
        telegramSenderAdapter.enviarTelegram(message,medioContacto.getContacto());
    }


}