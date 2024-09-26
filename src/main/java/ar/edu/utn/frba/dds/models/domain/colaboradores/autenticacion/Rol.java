package ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Rol {
  private String nombre;
  private List<Permiso> permisos = new ArrayList<>();

  public boolean tenesPermiso(Permiso permiso) {
    return this.permisos.contains(permiso);
  }

  public void agregarPermisos(Permiso... permisos) {
    this.permisos.addAll(Arrays.asList(permisos));
  }


}
