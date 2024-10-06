package ar.edu.utn.frba.dds.middleware;

import ar.edu.utn.frba.dds.dtos.UsuarioNavbarDto;
import ar.edu.utn.frba.dds.exceptions.NoAutorizadoException;
import ar.edu.utn.frba.dds.exceptions.UsuarioNoAutenticadoException;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Permiso;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.UsuarioService;
import ar.edu.utn.frba.dds.utils.PermisosHelper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.Set;

public class AuthMiddleware implements IMiddleware {
  public void apply(Javalin app) {
    app.beforeMatched(ctx -> {
      Set<Permiso> permisos = PermisosHelper.getInstance().buscarPermisosPara(ctx);
      Usuario usuario = getUser(ctx);
      ctx.attribute("currentUser", getUsuarioNavbar(usuario,ctx));
      if (!permisos.isEmpty()) {
        if (usuario == null) {
          ctx.sessionAttribute("previousUrl", ctx.path());
          throw new UsuarioNoAutenticadoException();
        }
        if (permisos.stream().noneMatch(usuario::tenesPermiso)) {
          throw new NoAutorizadoException("no esta autorizado");
        }
      }
    });
  }

  private static Usuario getUser(Context context) {
    String idUsuario = context.sessionAttribute("idUsuario");
    if (idUsuario == null) return null;
    return ServiceLocator.get(UsuarioService.class).
        obtenerUsuario(idUsuario);
  }

  private static UsuarioNavbarDto getUsuarioNavbar(Usuario u,Context ctx) {
    if (u == null) return null;
    return ServiceLocator.get(UsuarioService.class).getUsuarioNavbar(u,ctx.sessionAttribute("idColaborador"));
  }
}
