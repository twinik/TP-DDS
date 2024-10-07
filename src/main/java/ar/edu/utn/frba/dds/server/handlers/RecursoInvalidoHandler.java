package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.RecursoInvalidoException;
import ar.edu.utn.frba.dds.exceptions.UsuarioNoAutenticadoException;
import io.javalin.Javalin;
import java.util.HashMap;
import java.util.Map;

public class RecursoInvalidoHandler implements IHandler{
  @Override
  public void setHandle(Javalin app) {
    app.exception(RecursoInvalidoException.class, (e, context) -> {
      Map<String, String> model = new HashMap<>();
      model.put("message", e.getMessage());
      context.status(404);
      context.render("/404.hbs", model);
    });
  }
}
