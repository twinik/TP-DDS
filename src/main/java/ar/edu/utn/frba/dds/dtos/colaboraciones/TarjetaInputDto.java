package ar.edu.utn.frba.dds.dtos.colaboraciones;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TarjetaInputDto {
  private String codigo;

  public static TarjetaInputDto of(Context context) {
    return new TarjetaInputDto(context.formParam("codTarjeta"));
  }
}
