package ar.edu.utn.frba.dds.models.domain.colaboraciones.cargaMasiva;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import ar.edu.utn.frba.dds.models.domain.emailSending.MyEmail;
import ar.edu.utn.frba.dds.models.domain.emailSending.SendGridMailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class EnviarMailTest {
    private SendGridMailSender mailSender;

    @BeforeEach
    void setUp() throws IOException {
        this.mailSender = new SendGridMailSender();
    }

    @Test
    void enviarMail() {
        MyEmail email = MyEmail
                .builder()
                .emisor("grupo7ddsutn@gmail.com")
                .receptor("gturri@frba.utn.edu.ar")
                .asunto("Test")
                .cuerpo("Test SendGrid")
                .build();

        this.mailSender.enviarMail(email);
        assertDoesNotThrow(() -> this.mailSender.enviarMail(email));
    }
}
