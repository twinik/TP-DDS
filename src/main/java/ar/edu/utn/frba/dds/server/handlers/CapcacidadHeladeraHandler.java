package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.DniDuplicadoException;
import ar.edu.utn.frba.dds.exceptions.HeladeraLlenaException;
import ar.edu.utn.frba.dds.exceptions.HeladeraVaciaException;
import io.javalin.Javalin;

public class CapcacidadHeladeraHandler implements IHandler {
  @Override
  public void setHandle(Javalin app) {
    app.exception(HeladeraVaciaException.class, (e, context) -> {
      e.printStackTrace();
      context.redirect(context.path() + "?message=" + e.getMessage());
    });

    app.exception(HeladeraLlenaException.class, (e, context) -> {
      e.printStackTrace();
      context.redirect(context.path() + "?message=" + e.getMessage());
    });
  }
}
