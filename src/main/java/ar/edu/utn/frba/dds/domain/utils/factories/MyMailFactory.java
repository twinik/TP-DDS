package ar.edu.utn.frba.dds.domain.utils.factories;

import ar.edu.utn.frba.dds.domain.utils.MailSenderAdapter;
import ar.edu.utn.frba.dds.domain.utils.MyEmail;
import lombok.Builder;

/**
 * MyMailFactory class se encarga de crear mails.
 */

@Builder
public class MyMailFactory {

  /**
   * Metodo createMail que se encarga de crear un mail.
   */
  public static MyEmail createMail(String emisor, String receptor, String asunto, String cuerpo) {
    return MyEmail.builder()
        .emisor(emisor)
        .receptor(receptor)
        .asunto(asunto)
        .cuerpo(cuerpo)
        .build();
  }

  /**
   * Metodo sendMail que se encarga de crear y enviar un mail.
   */
  public static void sendMail(MailSenderAdapter mailSender,
                              String emisor, String receptor,
                              String asunto, String cuerpo) {
    MyEmail email = createMail(emisor, receptor, asunto, cuerpo);
    mailSender.enviarMail(email);
  }
}