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
    return DireccionDto
        .builder()
        .calle(context.formParam("calle"))
        .altura(Integer.parseInt(context.formParam("altura")))
        .piso( (context.formParam("piso") != null && !context.formParam("pisio").isBlank()) ? Integer.parseInt(context.formParam("piso")) : null)
        .codigoPostal(context.formParam("codigoPostal"))
        .build();
  }

  public static DireccionDto fromDireccion(Direccion direccion) {
    return DireccionDto
        .builder()
        .calle(direccion.getCalle())
        .altura(direccion.getAltura())
        .piso(direccion.getPiso())
        .codigoPostal(direccion.getCodigoPostal())
        .build();
  }


  public boolean estanCamposLlenos(Object formDto) {
    if (this.calle == null || this.altura == null || this.codigoPostal == null) {
      throw new FormIncompletoException(MensajeDirIncompletaFactory.generarMensaje(), formDto);
    }
    return true;
  }
}
