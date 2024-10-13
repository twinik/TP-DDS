package ar.edu.utn.frba.dds.dtos.heladeras;


import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class HeladeraDto {
  // TODO: completar compos
  private String id;
  private String nombre;
  private Boolean activa;

  public static HeladeraDto fromHeladera(Heladera heladera) {
    return HeladeraDto
        .builder()
        .id(heladera.getId())
        .nombre(heladera.getNombre())
        .activa(heladera.isHeladeraActiva())
        .build();
  }

  public static Heladera toHeladera(HeladeraDto dto) {
    Heladera heladera = new Heladera();
    heladera.setNombre(dto.getNombre());
    return heladera;
  }

  public static HeladeraDto of(Context context) {
    // TODO
    return null;
  }

}
