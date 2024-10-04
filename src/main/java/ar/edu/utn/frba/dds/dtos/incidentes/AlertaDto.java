package ar.edu.utn.frba.dds.dtos.incidentes;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.models.domain.incidentes.Alerta;
import lombok.Builder;
import lombok.Getter;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class AlertaDto {
  private String id;
  private HeladeraDto heladera;
  private String fechaHora;
  private String tipoAlerta;

  public static AlertaDto fromAlerta(Alerta a){
    return AlertaDto.builder()
            .id(a.getId())
            .heladera(HeladeraDto.fromHeladera(a.getHeladera()))
            .fechaHora(a.getTimestamp().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
            .tipoAlerta(a.getTipoAlerta().name().toLowerCase()) // mappear a un string sin mayus
            .build();
  }

  public static AlertaDto of(Map<String, List<String>> camposFormulario){
    // TODO
    return null;
  }

}
