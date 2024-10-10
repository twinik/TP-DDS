package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.EmailDuplicadoException;
import ar.edu.utn.frba.dds.models.domain.excepciones.NoTieneDireccionException;
import io.javalin.Javalin;

public class MailDuplicadoHandler implements IHandler {

  @Override
  public void setHandle(Javalin app) {
    app.exception(EmailDuplicadoException.class, (e, context) -> {
      context.redirect(context.path() + "?message=" + e.getMessage());
    });
  }
}
