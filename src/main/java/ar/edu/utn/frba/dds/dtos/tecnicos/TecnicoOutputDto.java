package ar.edu.utn.frba.dds.dtos.tecnicos;

import ar.edu.utn.frba.dds.models.domain.tecnicos.Tecnico;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TecnicoOutputDto {
  private String id;
  private String nombre;

  public static TecnicoOutputDto fromTecnico(Tecnico tecnico) {
    return TecnicoOutputDto.builder().id(tecnico.getId())
        .nombre(tecnico.getNombre() + " " + tecnico.getApellido())
        .build();
  }
}
