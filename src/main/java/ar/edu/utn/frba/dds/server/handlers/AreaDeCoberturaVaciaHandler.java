package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.AreaDeCoberturaVaciaException;
import ar.edu.utn.frba.dds.helpers.TildesHelper;
import io.javalin.Javalin;

public class AreaDeCoberturaVaciaHandler implements IHandler {
  @Override
  public void setHandle(Javalin app) {
    app.exception(AreaDeCoberturaVaciaException.class, (e, context) -> {
      e.printStackTrace();
      context.sessionAttribute("formDto", e.getFormDto());
      context.redirect(context.path() + "?message=" + TildesHelper.codificarParaQueryParam(e.getMessage()));
    });
  }
}
