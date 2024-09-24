package ar.edu.utn.frba.dds.models.domain.notifications;

import ar.edu.utn.frba.dds.models.domain.emailSending.SendGridMailSender;
import ar.edu.utn.frba.dds.models.domain.excepciones.InvalidNotificationStrategyException;
import ar.edu.utn.frba.dds.models.domain.telegramSender.BotTelegramSender;
import ar.edu.utn.frba.dds.models.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.models.domain.whatsappSender.TwilioWhatsappSender;
import lombok.NoArgsConstructor;
import java.io.IOException;

@NoArgsConstructor
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
