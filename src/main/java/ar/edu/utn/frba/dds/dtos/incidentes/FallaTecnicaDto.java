package ar.edu.utn.frba.dds.dtos.incidentes;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.models.domain.incidentes.FallaTecnica;
import io.javalin.http.Context;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
public class FallaTecnicaDto {
  private String id;
  private String heladeraId;
  private String heladeraNombre;
  private String descripcion;
  private String urlFoto;
  private String idColaborador;


  public static FallaTecnicaDto fromFalla(FallaTecnica f){
    return FallaTecnicaDto.builder()
        .id(f.getId())
        .heladeraId(f.getHeladera().getId())
        .heladeraNombre(f.getHeladera().getNombre())
        .descripcion(f.getDescripcion())
        .urlFoto(f.getUrlFoto())
        .idColaborador(f.getColaborador().getId())
        .build();
  }

  public static FallaTecnicaDto of(Context ctx){
    return FallaTecnicaDto.builder()
        .heladeraId(ctx.pathParam("id"))
        .descripcion(ctx.formParam("descripcion"))
        .idColaborador(ctx.sessionAttribute("idColaborador"))
        .build();
  }

}
