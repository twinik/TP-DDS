package ar.edu.utn.frba.dds.dtos.formularios;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AltaFormularioDto {
  List<AltaCampoFormularioDto> campos;

  public static AltaFormularioDto fromContext(Context ctx) {
    Integer cantidadCampos = Integer.parseInt(ctx.formParam("cantidad-campos"));
    List<AltaCampoFormularioDto> campos = new ArrayList<>();
    for (int i = 1; i <= cantidadCampos; i++) {
      campos.add(AltaCampoFormularioDto.fromContext(i, ctx));
    }
    return new AltaFormularioDto(campos);
  }
}
