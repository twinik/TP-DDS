package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.MismaHeladeraException;
import io.javalin.Javalin;

public class MismaHeladeraHandler implements IHandler {
  @Override
  public void setHandle(Javalin app) {
    app.exception(MismaHeladeraException.class, (e, context) -> {
      e.printStackTrace();
      context.redirect(context.path() + "?message=" + e.getMessage());
    });
  }
}
