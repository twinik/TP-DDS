package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.LoginDto;
import ar.edu.utn.frba.dds.dtos.UsuarioDto;
import ar.edu.utn.frba.dds.exceptions.LoginFailedException;
import ar.edu.utn.frba.dds.services.UsuarioService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.Objects;

@AllArgsConstructor
public class LoginController implements ICrudViewsHandler {
  private UsuarioService usuarioService;

  public void handleLogin(Context ctx) {
    UsuarioDto usuario = UsuarioDto.of(ctx);
    try {
      LoginDto dto = this.usuarioService.obtenerUsuario(usuario);
      ctx.sessionAttribute("idUsuario", dto.getIdUsuario());
      if(dto.getIdColaborador() != null) ctx.sessionAttribute("idColaborador",dto.getIdColaborador());
      // perdon uri no lo vi
      //String path = ctx.queryParam("nextPage") != null ? "/" + ctx.queryParam("nextPage") : "/";
     // ctx.redirect(path);
      String previousUrl = ctx.sessionAttribute("previousUrl");
      ctx.redirect(Objects.requireNonNullElse(previousUrl, "/"));
    } catch (LoginFailedException e) {
      // TODO: mostrar mensaje error
      ctx.redirect("/login");
    }
  }

  @Override
  public void index(Context context) {
    context.render("/auth/login/inicio-sesion.hbs");
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {

  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}
