package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.services.NoAutorizadoException;
import ar.edu.utn.frba.dds.services.UsuarioNoAutenticadoException;
import io.javalin.Javalin;

public class AccesoDenegadoHandler implements IHandler{
  @Override
  public void setHandle(Javalin app) {
    app.exception(UsuarioNoAutenticadoException.class, (e, context) -> {
      context.redirect("/auth/login/inicio-sesion.hbs");
    });
  }
}
