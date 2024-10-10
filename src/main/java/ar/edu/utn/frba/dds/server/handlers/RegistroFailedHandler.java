package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.RegistroFailedException;
import io.javalin.Javalin;

public class RegistroFailedHandler implements IHandler {

  @Override
  public void setHandle(Javalin app) {
    app.exception(RegistroFailedException.class, (e, context) -> {
      context.redirect(context.path() + "?message=" + e.getMessage());
    });
  }
}
