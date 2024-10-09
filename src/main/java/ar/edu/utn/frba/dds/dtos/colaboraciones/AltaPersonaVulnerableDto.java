package ar.edu.utn.frba.dds.dtos.colaboraciones;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class AltaPersonaVulnerableDto {
  private String nombre;
  private String apellido;
  private String tipoDocumento;
  private String nroDocumento;
  private String fechaNacimiento;
  private String domicilio;
  private String idColaborador;
  private String codigoTarjeta;
  private String tieneTutorados;
  private String cantidadTutorados;
  private List<AltaPersonaVulnerableDto> tutorados;

  public static AltaPersonaVulnerableDto of(Context context) {
    return AltaPersonaVulnerableDto
        .builder()
        .nombre(context.formParam("nombre"))
        .apellido(context.formParam("apellido"))
        .tipoDocumento(context.formParam("tipoDocumento"))
        .nroDocumento(context.formParam("nroDocumento"))
        .fechaNacimiento(context.formParam("fechaNacimiento"))
        .domicilio(context.formParam("domicilio"))
        .idColaborador(context.sessionAttribute("idColaborador"))
        .codigoTarjeta(context.formParam("codTarjeta"))
        .tieneTutorados(context.formParam("tiene-tutorados"))
        .cantidadTutorados(context.formParam("cantMenores"))
        // TODO Lista de tutorados
        .build();
  }
}
