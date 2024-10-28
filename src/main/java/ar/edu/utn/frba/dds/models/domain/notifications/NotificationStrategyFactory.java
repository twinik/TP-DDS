package ar.edu.utn.frba.dds.models.domain.notifications;

import ar.edu.utn.frba.dds.models.domain.emailSending.SendGridMailSender;
import ar.edu.utn.frba.dds.models.domain.excepciones.InvalidNotificationStrategyException;
import ar.edu.utn.frba.dds.models.domain.telegramSender.BotTelegramSender;
import ar.edu.utn.frba.dds.models.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.models.domain.whatsappSender.TwilioWhatsappSender;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotificationStrategyFactory {
  public NotificationStrategy create(CanalContacto canalContacto) {
    return switch (canalContacto) {
      case EMAIL -> new MailNotificationStrategy(ServiceLocator.get(SendGridMailSender.class));
      case WHATSAPP -> new WhatsappSenderStrategy(ServiceLocator.get(TwilioWhatsappSender.class));
      case TELEGRAM -> new TelegramNotificacionStrategy(ServiceLocator.get(BotTelegramSender.class));
      default -> throw new InvalidNotificationStrategyException();
    };
  }
}
