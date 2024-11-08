package ar.edu.utn.frba.dds.dtos.tecnicos;

import ar.edu.utn.frba.dds.dtos.incidentes.IncidenteDto;
import ar.edu.utn.frba.dds.models.domain.tecnicos.VisitaTecnico;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VisitaTecnicoListadoDto {
  private String id;
  private String nombreCompletoTecnico;
  private String fechaVisita;
  private String descripcion;
  private String urlFoto;
  private Boolean solucionado;
  private IncidenteDto incidente;

  public static VisitaTecnicoListadoDto fromVisita(VisitaTecnico visita) {
    return VisitaTecnicoListadoDto
        .builder()
        .id(visita.getId())
        .nombreCompletoTecnico(visita.getTecnico().getNombre() + " " + visita.getTecnico().getApellido())
        .fechaVisita(visita.getTimestamp().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
        .descripcion(visita.getDescripcion())
        .urlFoto(visita.getUrlFoto())
        .solucionado(visita.isSolucionado())
        .incidente(IncidenteDto.fromIncidente(visita.getIncidente()))
        .build();
  }
}
