package ar.edu.utn.frba.dds.models.domain.notifications;

import ar.edu.utn.frba.dds.models.domain.telegramSender.TelegramSenderAdapter;
import lombok.AllArgsConstructor;

/**
 * Estrategia de notificacion por telegram
 */
@AllArgsConstructor
public class TelegramNotificacionStrategy implements NotificationStrategy {

  private TelegramSenderAdapter telegramSenderAdapter;

  public void notificar(Contactable contactable, String asunto, String message) {
    telegramSenderAdapter.enviarTelegram(message, contactable.telegramId());
  }


}