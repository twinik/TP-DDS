package ar.edu.utn.frba.dds.dtos.colaboraciones;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import io.javalin.http.Context;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Builder
@Getter
public class IngresoViandaDto {
  private String idColaborador;
  private HeladeraDto heladeraDto;
  private List<ViandaDto> viandas;
  private String fechaDonacion;

  public static IngresoViandaDto of(Context context) {
    return IngresoViandaDto.builder()
        .heladeraDto(HeladeraDto.builder().id(context.formParam("idHeladera")).build())
        .idColaborador(context.sessionAttribute("idColaborador"))
        .fechaDonacion(context.formParam("fechaDonacion"))
        .viandas(ViandaDto.obtenerListaViandas(context))
        .build();
  }

}
