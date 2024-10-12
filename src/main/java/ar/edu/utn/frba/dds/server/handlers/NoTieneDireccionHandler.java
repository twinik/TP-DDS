package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.models.domain.excepciones.CodigoInvalidoException;
import ar.edu.utn.frba.dds.models.domain.excepciones.NoTieneDireccionException;
import io.javalin.Javalin;

public class NoTieneDireccionHandler implements IHandler {
  @Override
  public void setHandle(Javalin app) {
    app.exception(NoTieneDireccionException.class, (e, context) -> {
      e.printStackTrace();
      context.redirect(context.path() + "?message=" + e.getMessage());
    });


  }
}
