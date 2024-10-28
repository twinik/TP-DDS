package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.EmailDuplicadoException;
import io.javalin.Javalin;

public class MailDuplicadoHandler implements IHandler {

  @Override
  public void setHandle(Javalin app) {
    app.exception(EmailDuplicadoException.class, (e, context) -> {
      e.printStackTrace();
      context.sessionAttribute("formDto", e.getFormDto());
      context.redirect(context.path() + "?message=" + e.getMessage());
    });
  }
}
