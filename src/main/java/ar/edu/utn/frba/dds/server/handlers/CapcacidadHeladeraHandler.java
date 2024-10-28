package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.HeladeraLlenaException;
import ar.edu.utn.frba.dds.exceptions.HeladeraVaciaException;
import ar.edu.utn.frba.dds.helpers.TildesHelper;
import io.javalin.Javalin;

public class CapcacidadHeladeraHandler implements IHandler {
  @Override
  public void setHandle(Javalin app) {
    app.exception(HeladeraVaciaException.class, (e, context) -> {
      e.printStackTrace();
      context.sessionAttribute("formDto", e.getFormDto());
      context.redirect(context.path() + "?message=" + TildesHelper.codificarParaQueryParam(e.getMessage()));
    });

    app.exception(HeladeraLlenaException.class, (e, context) -> {
      e.printStackTrace();
      context.sessionAttribute("formDto", e.getFormDto());
      context.redirect(context.path() + "?message=" + TildesHelper.codificarParaQueryParam(e.getMessage()));
    });
  }
}
