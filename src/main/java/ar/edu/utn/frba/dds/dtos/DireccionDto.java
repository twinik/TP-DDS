package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeDirIncompletaFactory;
import io.javalin.http.Context;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DireccionDto {
  private String calle;
  private Integer altura;
  private Integer piso;
  private String codigoPostal;

  public static DireccionDto of(Context context) {
    return (context.formParam("calle") != null && !context.formParam("calle").isBlank() ||
        context.formParam("altura") != null && !context.formParam("altura").isBlank() ||
        context.formParam("cp") != null && !context.formParam("cp").isBlank() ||
        context.formParam("piso") != null && !context.formParam("piso").isBlank()) ? DireccionDto
        .builder()
        .calle(context.formParam("calle"))
        .altura(Integer.parseInt(context.formParam("altura")))
        .piso((context.formParam("piso") != null && !context.formParam("piso").isBlank()) ? Integer.parseInt(context.formParam("piso")) : null)
        .codigoPostal(context.formParam("codigoPostal"))
        .build() : null;
  }

  public static DireccionDto fromDireccion(Direccion direccion) {
    return direccion != null ? DireccionDto
        .builder()
        .calle(direccion.getCalle())
        .altura(direccion.getAltura())
        .piso(direccion.getPiso())
        .codigoPostal(direccion.getCodigoPostal())
        .build() : null;
  }


  public boolean estanCamposLlenos(Object formDto) {
    if (this.calle == null || this.altura == null || this.codigoPostal == null) {
      throw new FormIncompletoException(MensajeDirIncompletaFactory.generarMensaje(), formDto);
    }
    return true;
  }
}
