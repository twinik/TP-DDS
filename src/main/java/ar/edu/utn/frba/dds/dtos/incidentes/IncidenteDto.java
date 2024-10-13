package ar.edu.utn.frba.dds.dtos.incidentes;

import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import lombok.Builder;
import lombok.Getter;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
public class IncidenteDto {
  private String nombreHeladera;
  private String fecha;
  private String hora;

  private String id;
  private String tipo;


  public static IncidenteDto fromIncidente(Incidente i) {
    return IncidenteDto.builder().id(i.getId())
        .nombreHeladera(i.getHeladera().getNombre())
        .fecha(i.getTimestamp().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
        .hora(i.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        .tipo(i.getTipo())
        .build();
  }

}
