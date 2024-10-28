package ar.edu.utn.frba.dds.middleware;

import io.javalin.Javalin;

public interface IMiddleware {
  void apply(Javalin app);
}
