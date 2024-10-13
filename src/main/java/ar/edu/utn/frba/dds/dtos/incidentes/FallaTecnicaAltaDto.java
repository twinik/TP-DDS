package ar.edu.utn.frba.dds.dtos.incidentes;

import io.javalin.http.Context;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FallaTecnicaAltaDto {
  private String id;
  private String descripcion;
  private String urlFoto;
  private String idColaborador;
  private String heladeraId;

  public static FallaTecnicaAltaDto of(Context ctx) {
    return FallaTecnicaAltaDto.builder()
        .heladeraId(ctx.pathParam("id"))
        .descripcion(ctx.formParam("descripcion"))
        .idColaborador(ctx.sessionAttribute("idColaborador"))
        .build();
  }
}
