package ar.edu.utn.frba.dds.domain.notifications;

import ar.edu.utn.frba.dds.domain.emailSending.SendGridMailSender;
import ar.edu.utn.frba.dds.domain.excepciones.InvalidNotificationStrategyException;
import ar.edu.utn.frba.dds.domain.telegramSender.BotTelegramSender;
import ar.edu.utn.frba.dds.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.domain.whatsappSender.TwilioWhatsappSender;
import java.io.IOException;

public class NotificationStrategyFactory {
  public NotificationStrategy create(CanalContacto canalContacto) {
    try {
      return switch (canalContacto) {
        case EMAIL -> new MailNotificationStrategy(new SendGridMailSender());
        case WHATSAPP -> new WhatsappSenderStrategy(new TwilioWhatsappSender());
        case TELEGRAM -> new TelegramNotificacionStrategy(new BotTelegramSender());
        default -> throw new InvalidNotificationStrategyException();
      };
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
}
