package ar.edu.utn.frba.dds.domain.emailSending;

/**
 * MailSenderAdapter interface permite enviar un mail.
 */
public interface MailSenderAdapter {
  void enviarMail(MyEmail mail);
}
