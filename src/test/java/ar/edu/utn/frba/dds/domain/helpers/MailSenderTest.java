package ar.edu.utn.frba.dds.domain.helpers;

import ar.edu.utn.frba.dds.domain.utils.MyEmail;
import ar.edu.utn.frba.dds.domain.utils.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MailSenderTest {
  private static MyEmail email;
  private static MailSender mailSender;

  @BeforeEach
  void setUp() {
    email = new MyEmail("grupo7ddsutn@gmail.com", "gturri@frba.utn.edu.ar", "Hola Gon desde TP DDS", "Cuerpo del correo");
    mailSender = new MailSender("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");
  }

  @Test
  @DisplayName("Envio de mail exitoso")
  void testEnvioMailExitoso() {
      mailSender.enviarMail(email);
  }
}
