package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.UsuarioNoAutenticadoException;
import io.javalin.Javalin;

public class AccesoDenegadoHandler implements IHandler {
  @Override
  public void setHandle(Javalin app) {
    app.exception(UsuarioNoAutenticadoException.class, (e, context) -> {
      e.printStackTrace();
      context.redirect("/login");
    });
  }
}
