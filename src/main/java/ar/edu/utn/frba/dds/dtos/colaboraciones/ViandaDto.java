package ar.edu.utn.frba.dds.dtos.colaboraciones;

import io.javalin.http.Context;
import lombok.Builder;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class ViandaDto {
  private String desc;
  private String fechaCaducidad;
  private Integer calorias;
  private Float peso;

  public static List<ViandaDto> obtenerListaViandas(Context context) {
    List<ViandaDto> result = new ArrayList<>();
    int cantidadViandas = Integer.parseInt(context.formParam("cant-viandas"));
    for (int i = 1; i <= cantidadViandas; i++) {
      result.add(ViandaDto.builder().
          desc(context.formParam("desc-" + i))
          .fechaCaducidad(context.formParam("fechaCaducidad-" + i))
          .calorias((context.formParam("calorias-" + i) != null && !context.formParam("calorias-" + i).isBlank()) ? Integer.valueOf(context.formParam("calorias-" + i)) : null)
          .peso((context.formParam("peso-" + i) != null && !context.formParam("peso-" + i).isBlank()) ? Float.valueOf(context.formParam("peso-" + i)) : null)
          .build());
    }
    return result;
  }

  public boolean estanCamposLlenos() {
    return this.desc != null && this.fechaCaducidad != null;
  }

}
