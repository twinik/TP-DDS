package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import io.javalin.Javalin;
import java.util.HashMap;
import java.util.Map;

public class RecursoInexistenteHandler implements IHandler{
  @Override
  public void setHandle(Javalin app) {
    app.exception(RecursoInexistenteException.class, (e, context) -> {
      Map<String, String> model = new HashMap<>();
      model.put("message", e.getMessage());
      context.status(404);
      context.render("app/404.hbs", model);
    });
  }
}
