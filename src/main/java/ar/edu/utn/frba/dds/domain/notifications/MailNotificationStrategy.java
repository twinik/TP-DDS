package ar.edu.utn.frba.dds.domain.notifications;

import ar.edu.utn.frba.dds.domain.emailSending.MailSenderAdapter;
import ar.edu.utn.frba.dds.domain.emailSending.MyMailFactory;
import ar.edu.utn.frba.dds.domain.excepciones.CrearMailException;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import lombok.AllArgsConstructor;
import java.io.IOException;

/**
 *
 */
@AllArgsConstructor
public class MailNotificationStrategy implements NotificationStrategy {
    private MailSenderAdapter mailSenderAdapter;

    /**
     * @param contactable
     */
    @Override
    public void notificar(Contactable contactable, String message) {
        ConfigReader config = new ConfigReader("config.properties");
        try {
            mailSenderAdapter.enviarMail(MyMailFactory.createMail(config.getProperty("MAIL-DIR")
                    , contactable.email(), config.getProperty("ASUNTO_MENSAJE_TENICO"), message));
        } catch (IOException e) {
            throw new CrearMailException(e);
        }
    }
}