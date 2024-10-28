package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.AreaDeCoberturaVaciaException;
import ar.edu.utn.frba.dds.exceptions.DniDuplicadoException;
import ar.edu.utn.frba.dds.helpers.TildesHelper;
import io.javalin.Javalin;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class AreaDeCoberturaVaciaHandler implements IHandler{
  @Override
  public void setHandle(Javalin app) {
    app.exception(AreaDeCoberturaVaciaException.class, (e, context) -> {
      e.printStackTrace();
      context.sessionAttribute("formDto", e.getFormDto());
      context.redirect(context.path() + "?message=" + TildesHelper.codificarParaQueryParam(e.getMessage()));
    });
  }
}
