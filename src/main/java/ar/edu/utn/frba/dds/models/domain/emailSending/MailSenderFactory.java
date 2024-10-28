package ar.edu.utn.frba.dds.models.domain.emailSending;

import java.io.IOException;

/**
 * Permite crear objetos que implementen la interfaz MailSenderAdapter
 */
public class MailSenderFactory {

  /**
   * Factory Method, crea un SendGridMailSender por default
   */
  public static MailSenderAdapter createMailSender() throws IOException {
    return new SendGridMailSender();
  }
}
