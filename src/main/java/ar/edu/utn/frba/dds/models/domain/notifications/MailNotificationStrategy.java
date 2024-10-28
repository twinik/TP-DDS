package ar.edu.utn.frba.dds.models.domain.notifications;

import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.models.domain.emailSending.MailSenderAdapter;
import ar.edu.utn.frba.dds.models.domain.emailSending.MyMailFactory;
import ar.edu.utn.frba.dds.models.domain.excepciones.CrearMailException;
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
  public void notificar(Contactable contactable, String asunto, String message) {
    ConfigReader config = new ConfigReader("config.properties");
    try {
      mailSenderAdapter.enviarMail(MyMailFactory.createMail(config.getProperty("MAIL-DIR")
          , contactable.email(), asunto, message));
    } catch (IOException e) {
      throw new CrearMailException(e);
    }
  }
}