package ar.edu.utn.frba.dds.dtos.colaboraciones;

import io.javalin.http.Context;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TutoradoInputDto {
  private String nombre;
  private String apellido;
  private String tipoDocumento;
  private String nroDocumento;
  private String fechaNacimiento;
  private String domicilio;
  private String codigoTarjeta;
  private String idColaborador;

  public static TutoradoInputDto of(Context context, Integer numeroMenor) {
    return TutoradoInputDto
        .builder()
        .nombre(context.formParam("nombre-" + numeroMenor))
        .apellido(context.formParam("apellido-" + numeroMenor))
        .tipoDocumento(context.formParam("tipoDocumento-" + numeroMenor))
        .nroDocumento(context.formParam("nroDocumento-" + numeroMenor))
        .fechaNacimiento(context.formParam("fechaNacimiento-" + numeroMenor))
        .domicilio(context.sessionAttribute("domicilioFamiliaVulnerable"))
        .codigoTarjeta(context.formParam("codTarjeta-" + numeroMenor))
        .idColaborador(context.sessionAttribute("idColaborador"))
        .build();
  }
}
