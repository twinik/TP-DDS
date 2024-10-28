package ar.edu.utn.frba.dds.dtos.formularios;

import io.javalin.http.Context;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RespuestaACampoDto {
  private String idCampo;
  private String respuesta;
  private List<String> idOpciones;

  public static List<RespuestaACampoDto> listFromContext(Context ctx) {
    List<RespuestaACampoDto> dtos = new ArrayList<>();
    Map<String, List<String>> params = ctx.formParamMap();
    params.forEach((key, value) -> {
      if (key.startsWith("campo_")) {
        RespuestaACampoDto dto = new RespuestaACampoDto();
        dto.setIdCampo(key.split("_")[1]);
        if (value.size() > 1) {
          dto.setIdOpciones(value); // si es un multiple choice
        } else {
          dto.setRespuesta(value.get(0));
          dto.setIdOpciones(value);
        }
        dtos.add(dto);
      }
    });
    return dtos;
  }
}
