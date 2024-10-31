package ar.edu.utn.frba.dds.models.domain.helpers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import ar.edu.utn.frba.dds.models.domain.emailSending.MailSenderAdapter;
import ar.edu.utn.frba.dds.models.domain.emailSending.MyEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MailSenderTest {
  private static MyEmail email;
  private MailSenderAdapter mailSender;

  @BeforeEach
  void setUp() {
    email = new MyEmail("grupo7ddsutn@gmail.com", "twinik@frba.utn.edu.ar", "Hola Gon desde TP DDS", "Cuerpo del correo");
    mailSender = mock(MailSenderAdapter.class);
    doNothing().when(mailSender).enviarMail(any());
  }

  @Test
  @DisplayName("Envio de mail exitoso")
  void testEnvioMailExitoso() {
    mailSender.enviarMail(email);
    verify(mailSender, times(1)).enviarMail(email);
  }
}
