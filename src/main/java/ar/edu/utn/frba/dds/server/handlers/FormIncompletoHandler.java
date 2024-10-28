package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.helpers.TildesHelper;
import io.javalin.Javalin;

public class FormIncompletoHandler implements IHandler {

  @Override
  public void setHandle(Javalin app) {
    app.exception(FormIncompletoException.class, (e, context) -> {
      e.printStackTrace();
      context.sessionAttribute("formDto", e.getFormDto());
      context.redirect(context.path() + "?message=" + TildesHelper.codificarParaQueryParam(e.getMessage()));
    });
  }

}
