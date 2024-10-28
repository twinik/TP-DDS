package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.ClaveDebilException;
import ar.edu.utn.frba.dds.exceptions.ClaveNoCoincidenException;
import ar.edu.utn.frba.dds.helpers.TildesHelper;
import io.javalin.Javalin;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClaveHandler implements IHandler {
  @Override
  public void setHandle(Javalin app) {
    app.exception(ClaveDebilException.class, (e, context) -> {
      e.printStackTrace();
      context.sessionAttribute("formDto", e.getFormDto());
      context.redirect(context.path() + "?message=" + TildesHelper.codificarParaQueryParam(e.getMessage()));
    });
    app.exception(ClaveNoCoincidenException.class, (e, context) -> {
      e.printStackTrace();
      context.sessionAttribute("formDto", e.getFormDto());
      context.redirect(context.path() + "?message=" + e.getMessage());
    });
  }
}
