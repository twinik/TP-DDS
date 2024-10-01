package ar.edu.utn.frba.dds.middleware;

import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Permiso;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.repositories.IUsuariosRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.NoAutorizadoException;
import ar.edu.utn.frba.dds.services.UsuarioNoAutenticadoException;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class AuthMiddleware {
  public static void apply(Javalin app) {
    app.beforeMatched(ctx -> {
      Usuario usuario = getUser(ctx);

      if(usuario == null) throw new UsuarioNoAutenticadoException();

      if (!ctx.routeRoles().isEmpty() && ctx.routeRoles().stream().noneMatch(permiso -> usuario.tenesPermiso((Permiso) permiso))) {
        throw new NoAutorizadoException("no esta autorizado");
      }
    });
  }

  private static Usuario getUser(Context context) {
    if (context.sessionAttribute("idUsuario") == null) return null;
    return ServiceLocator.get(IUsuariosRepository.class).
        buscar(context.sessionAttribute("idUsuario")).orElse(null);
  }
}
