package ar.edu.utn.frba.dds.domain.notifications;

import ar.edu.utn.frba.dds.domain.emailSending.MailSenderAdapter;
import ar.edu.utn.frba.dds.domain.emailSending.MyEmail;
import ar.edu.utn.frba.dds.domain.emailSending.MyMailFactory;
import ar.edu.utn.frba.dds.domain.excepciones.CrearMailException;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
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
     * @param medioContacto
     */
    @Override
    public void notificar(MedioDeContacto medioContacto, String message) {
        ConfigReader config = new ConfigReader("config.propertie");
        try {
            mailSenderAdapter.enviarMail(MyMailFactory.createMail(config.getProperty("MAIL-DIR")
                , medioContacto.getContacto(),config.getProperty("ASUNTO_MENSAJE_TENICO"),message));
        } catch (IOException e) {
            throw new CrearMailException(e);
        }
    }
}