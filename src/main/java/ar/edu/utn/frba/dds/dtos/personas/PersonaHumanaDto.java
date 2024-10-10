package ar.edu.utn.frba.dds.dtos.personas;

import ar.edu.utn.frba.dds.dtos.DireccionDto;
import ar.edu.utn.frba.dds.dtos.usuarios.UsuarioDto;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PersonaHumanaDto {
  private String nombre;
  private String apellido;
  private String fechaNacimiento;
  private String tipoDocumento;
  private String nroDocumento;
  private DireccionDto direccion;
  private List<FormaColaboracionDto> formasColaboracion;
  private List<MedioContactoDto> mediosDeContacto;
  private UsuarioDto usuarioDto;
  private String claveConf;

  public static PersonaHumanaDto of(Context context) {
//    String email = ctx.formParam("email");
//    String clave = ctx.formParam("password");
//    String claveConf = ctx.formParam("passConf");
//    String nombre = ctx.formParam("nombre");
//    String apellido = ctx.formParam("apellido");
//    LocalDate fechaNacimiento = fechaFromString(ctx.formParam("fechaNacimiento"), "dd/MM/yyyy");
//    String calle = ctx.formParam("calle");
//    Integer altura = Integer.parseInt(ctx.formParam("altura"));
//    Integer piso = Integer.parseInt(ctx.formParam("piso"));
//    String codigoPostal = ctx.formParam("cp");
//    Direccion direccion = new Direccion(calle, altura, piso, codigoPostal);
    return PersonaHumanaDto.builder().nombre(context.formParam("nombre"))
        .apellido(context.formParam("apellido"))
        .fechaNacimiento(context.formParam("fechaNacimiento"))
        .direccion((context.formParam("calle") != null && !context.formParam("calle").isBlank() ||
            context.formParam("altura") != null && !context.formParam("altura").isBlank() ||
            context.formParam("cp") != null && !context.formParam("cp").isBlank() ||
            context.formParam("piso") != null && !context.formParam("piso").isBlank())
            ? DireccionDto.builder()
            .calle((context.formParam("calle") != null && !context.formParam("calle").isBlank()) ? context.formParam("calle") : null)
            .numero((context.formParam("altura") != null && !context.formParam("altura").isBlank()) ? Integer.parseInt(context.formParam("altura")) : null)
            .piso((context.formParam("piso") != null && !context.formParam("piso").isBlank()) ? Integer.valueOf(context.formParam("piso")) : null)
            .codigoPostal((context.formParam("cp") != null && !context.formParam("cp").isBlank()) ? context.formParam("cp") : null)
            .build()
            : null)
        .tipoDocumento(context.formParam("tipoDocumento"))
        .nroDocumento(context.formParam("documento"))
        .claveConf(context.formParam("passConf"))
        .usuarioDto(new UsuarioDto(context.formParam("email"), context.formParam("password")))
        .mediosDeContacto(MedioContactoDto.of(context))
        .formasColaboracion(FormaColaboracionDto.of(context))
        .build();
  }

  public boolean sonClavesIguales() {
    return this.claveConf.equals(this.usuarioDto.getClave());
  }

  public boolean estanCamposLlenos() {
    return this.nombre != null && this.apellido != null && (this.direccion == null || this.direccion.estanCamposLlenos()) && this.usuarioDto != null &&
        this.formasColaboracion != null && MedioContactoDto.estanCamposLLenos(this.mediosDeContacto) && this.nroDocumento != null && this.tipoDocumento != null;
  }

}
