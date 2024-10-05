package ar.edu.utn.frba.dds.dtos.colaboraciones;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class DonacionDineroInputDto {
  private String fecha;
  private Float monto;
  private Integer FrecuenciaDonacion;

  public static DonacionDineroInputDto of(Map<String, List<String>> camposFormulario) {
    if (camposFormulario.get("frec").get(0) != null) {
      return new DonacionDineroInputDto(camposFormulario.get("fecha").get(0), Float.valueOf(camposFormulario.get("monto").get(0)),
          Integer.valueOf(camposFormulario.get("frec").get(0)));

    }
    return new DonacionDineroInputDto(camposFormulario.get("fecha").get(0), Float.valueOf(camposFormulario.get("monto").get(0)),
        null);
  }

}
