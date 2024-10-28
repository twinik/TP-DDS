package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.helpers.TildesHelper;
import ar.edu.utn.frba.dds.models.domain.excepciones.CodigoInvalidoException;
import io.javalin.Javalin;

public class CodigoInvalidoHandler implements IHandler {


  @Override
  public void setHandle(Javalin app) {
    app.exception(CodigoInvalidoException.class, (e, context) -> {
      e.printStackTrace();
      context.sessionAttribute("formDto", e.getFormDto());
      context.redirect(context.path() + "?message=" + TildesHelper.codificarParaQueryParam(e.getMessage()));
    });
  }
}
