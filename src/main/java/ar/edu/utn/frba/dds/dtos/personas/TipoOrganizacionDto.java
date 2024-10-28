package ar.edu.utn.frba.dds.dtos.personas;


import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersonaJuridica;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TipoOrganizacionDto {
  private String valor;

  public static TipoOrganizacionDto fromTipoOrganizacion(TipoPersonaJuridica tipo) {
    return new TipoOrganizacionDto(tipo.name());
  }
}
