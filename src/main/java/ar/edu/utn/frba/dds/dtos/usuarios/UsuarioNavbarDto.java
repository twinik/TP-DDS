package ar.edu.utn.frba.dds.dtos.usuarios;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsuarioNavbarDto {
  private String nombre;
  private String email;
  private Boolean permisoTecnico = false;
  private Boolean permisoFormulario = false;
  private Boolean permisoModeloHeladera = false;
  private Boolean permisoCodTarjeta = false;

  public Boolean esAdmin() {
    return this.permisoTecnico || this.permisoFormulario || this.permisoModeloHeladera || this.permisoCodTarjeta;
  }
}
