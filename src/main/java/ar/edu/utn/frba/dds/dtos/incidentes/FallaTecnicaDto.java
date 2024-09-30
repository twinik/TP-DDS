package ar.edu.utn.frba.dds.dtos.incidentes;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.models.domain.incidentes.FallaTecnica;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
import java.util.Map;

@Builder
@Getter
public class FallaTecnicaDto {
  private String id;
  private HeladeraDto heladera;
  private String descripcion;
  private String urlFoto;

  // TODO: cambiar en todos lados por colaboradorDto ???
  private String idColaborador;


  public static FallaTecnicaDto fromFalla(FallaTecnica f){
    return FallaTecnicaDto.builder()
        .id(f.getId())
        .heladera(HeladeraDto.fromHeladera(f.getHeladera()))
        .descripcion(f.getDescripcion())
        .urlFoto(f.getUrlFoto())
        .idColaborador(f.getColaborador().getId())
        .build();
  }

  public static FallaTecnicaDto of(Map<String, List<String>> camposFormulario, String idColaborador){
    // TODO
    return null;
  }

}
