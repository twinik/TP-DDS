package ar.edu.utn.frba.dds.dtos.heladeras;


import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class HeladeraDto {
  // TODO: completar compos
  private String id;
  private String nombre;

  public static HeladeraDto fromHeladera (Heladera heladera){
    return HeladeraDto.builder().
        id(heladera.getId())
        .nombre(heladera.getNombre())
        .build();
  }

    public static Heladera toHeladera(HeladeraDto dto){
        Heladera heladera = new Heladera();
        heladera.setNombre(dto.getNombre());
        return heladera;
    }

  public static HeladeraDto of(Map<String, List<String>> camposFormulario, String idColaborador){
    // TODO
    return null;
  }

}
