package ar.edu.utn.frba.dds.models.converters;

import ar.edu.utn.frba.dds.models.domain.notifications.MailNotificationStrategy;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.models.domain.notifications.TelegramNotificacionStrategy;
import ar.edu.utn.frba.dds.models.domain.notifications.WhatsappSenderStrategy;
import ar.edu.utn.frba.dds.models.domain.utils.CanalContacto;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class NotificationStrategyAttributeConverter implements AttributeConverter<NotificationStrategy, String> {
    @Override
    public String convertToDatabaseColumn(NotificationStrategy notificationStrategy) {
        if(notificationStrategy == null) return null;
        if (notificationStrategy instanceof MailNotificationStrategy) return "EMAIL";
        if (notificationStrategy instanceof TelegramNotificacionStrategy) return "TELEGRAM";
        if (notificationStrategy instanceof WhatsappSenderStrategy) return "WHATSAPP";
        return null;
    }

    @Override
    public NotificationStrategy convertToEntityAttribute(String s) {
        if(s == null) return null;
        NotificationStrategyFactory factory = new NotificationStrategyFactory();
        switch (s) {
            case "EMAIL" -> {
                return factory.create(CanalContacto.EMAIL);
            }
            case "TELEGRAM" -> {
                return factory.create(CanalContacto.TELEGRAM);
            }
            case "WHATSAPP" -> {
                return factory.create(CanalContacto.WHATSAPP);
            }
            default -> {
                return null;
            }
        }
    }
}
