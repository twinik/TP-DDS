package ar.edu.utn.frba.dds.server;

import io.javalin.Javalin;

public class Router {
  public static void init(Javalin app) {
    app.get("/", ctx -> {
      ctx.result("Hello World");
    });
  }
}
