package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoColaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Permiso;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Rol;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.repositories.IRolesRepository;
import ar.edu.utn.frba.dds.utils.PermisosHelper;
import lombok.AllArgsConstructor;
import java.util.UUID;

@AllArgsConstructor
public class RolesService {
  private IRolesRepository rolesRepository;

  public Rol obtnerRolPara(TipoColaborador tipoColaborador) {
    Rol r = new Rol();
    // TODO si quieren lo pueden cambiar
    r.setNombre(tipoColaborador.getTipo().name() + "-" + UUID.randomUUID());
    r.agregarPermisos(PermisosHelper.getInstance().buscarPorNombres("colaborador-base").toArray(new Permiso[0])); // que es esto loco
    tipoColaborador.getFormasPosiblesColaboracion().forEach(forma -> {
      Permiso[] permisos = PermisosHelper.getInstance().buscarPorNombres(PermisosHelper.getInstance().fromFormaColaboracion(forma).toArray(new String[0])).toArray(new Permiso[0]);
      r.agregarPermisos(permisos);
    });
    this.rolesRepository.guardar(r);
    return r;
  }

  public void agregarRolPara(Usuario u, Rol r) {
    this.rolesRepository.guardar(r);
    u.agregarRoles(r);
  }


}
