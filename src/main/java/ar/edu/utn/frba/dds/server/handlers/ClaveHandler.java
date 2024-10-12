package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.ClaveDebilException;
import ar.edu.utn.frba.dds.exceptions.ClaveNoCoincidenException;
import io.javalin.Javalin;
import lombok.NoArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class ClaveHandler implements IHandler {
  @Override
  public void setHandle(Javalin app) {
    app.exception(ClaveDebilException.class, (e, context) -> {
      e.printStackTrace();
      context.redirect(context.path() + "?message=" + e.getMessage());
    });
    app.exception(ClaveNoCoincidenException.class, (e, context) -> {
      e.printStackTrace();
      context.redirect(context.path() + "?message=" + "Las claves ingresadas no coinciden");
    });
  }
}
