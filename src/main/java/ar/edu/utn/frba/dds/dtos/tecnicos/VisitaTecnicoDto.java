package ar.edu.utn.frba.dds.dtos.tecnicos;
/*
        VisitaTecnico visita = new VisitaTecnico(dto.getTecnico(), dto.getFechaVisita(), dto.getDescripcion(), dto.getUrlFoto(), dto.isSolucionado(), dto.getIncidente());

 */

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VisitaTecnicoDto {

  private String idTecnico;
  private String fechaVisita;
  private String descripcion;
  private String urlFoto;
  private boolean solucionado;
  private String incidente;

  public static VisitaTecnicoDto of(Context context) {
    String idTecnico = context.formParam("tecnico");
    String fechaVisita = context.formParam("fecha");
    String descripcion = context.formParam("desc");
    String incidente = context.formParam("incidente");
    boolean solucionado = "Sí".equals(context.formParam("incidenteSolucionado"));

    return new VisitaTecnicoDto(idTecnico, fechaVisita, descripcion, null, solucionado, incidente);
  }
}
