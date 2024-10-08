package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.ClaveDebilException;
import ar.edu.utn.frba.dds.exceptions.ClaveNoCoincidenException;
import io.javalin.Javalin;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClaveHandler implements IHandler {
  @Override
  public void setHandle(Javalin app) {
    app.exception(ClaveDebilException.class, (e, context) -> {
      context.result(e.getMessage());
    });
    app.exception(ClaveNoCoincidenException.class, (e, context) -> {
      context.result("las claves ingresadas no coinciden");
    });
  }
}
