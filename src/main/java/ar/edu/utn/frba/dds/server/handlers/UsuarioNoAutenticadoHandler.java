package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.NoAutorizadoException;
import io.javalin.Javalin;

public class UsuarioNoAutenticadoHandler implements IHandler{
  @Override
  public void setHandle(Javalin app) {
    app.exception(NoAutorizadoException.class, (e, context) -> {
      context.status(401);
      // TODO: HACER PANTALLA
      context.render("/app/401.hbs");
    });
  }
}
