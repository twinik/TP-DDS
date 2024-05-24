package ar.edu.utn.frba.dds.domain.utils;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;

/**
 * MailSender class permite enviar un mail.
 */
public class MailSender implements MailSenderAdapter {
  private static String apiKey;

  public MailSender() {
    apiKey = "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"; //TODO sacar de un config file
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
      throw new RuntimeException(e);
    }
  }
}
