package ar.edu.utn.frba.dds.middleware;

import ar.edu.utn.frba.dds.dtos.UsuarioNavbarDto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersona;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Permiso;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.models.repositories.IUsuariosRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.exceptions.NoAutorizadoException;
import ar.edu.utn.frba.dds.exceptions.UsuarioNoAutenticadoException;
import ar.edu.utn.frba.dds.utils.PermisosHelper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.Optional;
import java.util.Set;

public class AuthMiddleware {
  public static void apply(Javalin app) {
    app.beforeMatched(ctx -> {
      Set<Permiso> permisos = PermisosHelper.getInstance().buscarPermisosPara(ctx);
      Usuario usuario = getUser(ctx);
      ctx.attribute("currentUser", getUsuarioNavbar(usuario));
      if(!permisos.isEmpty()){
        if(usuario == null) throw new UsuarioNoAutenticadoException();
        if (permisos.stream().noneMatch(usuario::tenesPermiso)) {
          throw new NoAutorizadoException("no esta autorizado");
        }
      }
    });
  }

  private static Usuario getUser(Context context) {
    if (context.sessionAttribute("idUsuario") == null) return null;
    return ServiceLocator.get(IUsuariosRepository.class).
        buscar(context.sessionAttribute("idUsuario")).orElse(null);
  }

  private static UsuarioNavbarDto getUsuarioNavbar(Usuario u) {
    if (u == null) return null;
    Optional<Colaborador> colaborador = ServiceLocator.get(IColaboradoresRepository.class).buscarPorUsuario(u.getId());
    UsuarioNavbarDto resultado = new UsuarioNavbarDto();
    resultado.setEmail(u.getEmail());
    if (colaborador.isPresent()) {
      if (colaborador.get().getTipoColaborador().getTipo() == TipoPersona.PERSONA_HUMANA) {
        resultado.setNombre(colaborador.get().getNombreYapellido());
      } else {
        resultado.setNombre(colaborador.get().getRazonSocial());
      }
    } else {
      resultado.setNombre("Usuario");
    }
    return resultado;
  }
}
