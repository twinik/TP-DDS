package ar.edu.utn.frba.dds.domain;

import ar.edu.utn.frba.dds.domain.utils.Email;

public interface MailSenderAdapter {
  void enviarMail(Email mail);
}
