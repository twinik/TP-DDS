package ar.edu.utn.frba.dds.controllers;

import io.javalin.http.Context;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LogoutController {
  public void handleLogout(Context ctx) {
    ctx.req().getSession().invalidate();
    ctx.redirect("/");
  }
}
