package ar.edu.utn.frba.dds.models.domain.emailSending;

import ar.edu.utn.frba.dds.models.domain.excepciones.CrearMailException;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;

/**
 * MailSender class permite enviar un mail.
 */
public class SendGridMailSender implements MailSenderAdapter {
    private final String apiKey;

    public SendGridMailSender() throws IOException {
        this.apiKey = new ConfigReader("config.properties").getProperty("sendgrid.apikey");
    }

    @Override
    public void enviarMail(MyEmail email) {
        Email from = new Email(email.getEmisor());
        String subject = email.getAsunto();
        Email to = new Email(email.getReceptor());
        Content content = new Content("text/plain", email.getCuerpo());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Body: " + response.getBody());
            System.out.println("Headers: " + response.getHeaders());
        } catch (IOException e) {
            throw new CrearMailException(e);
        }
    }
}
