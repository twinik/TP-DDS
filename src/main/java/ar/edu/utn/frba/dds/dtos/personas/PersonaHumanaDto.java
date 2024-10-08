package ar.edu.utn.frba.dds.dtos.personas;

import ar.edu.utn.frba.dds.dtos.DIreccionDto;
import ar.edu.utn.frba.dds.dtos.usuarios.UsuarioDto;
import ar.edu.utn.frba.dds.models.domain.utils.MedioDeContacto;
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
  private DIreccionDto direccion;
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
        .direccion(DIreccionDto.builder().calle(context.formParam("calle"))
            .numero(Integer.parseInt(context.formParam("altura")))
            .piso((context.formParam("piso") != null && !context.formParam("piso").isBlank()) ? Integer.valueOf(context.formParam("piso")) : null)
            .codigoPostal(context.formParam("cp"))
            .build())
        .claveConf(context.formParam("passConf"))
        .usuarioDto(new UsuarioDto(context.formParam("email"), context.formParam("password")))
        .mediosDeContacto(MedioContactoDto.of(context))
        .build();
  }

  public boolean sonClavesIguales() {
    return this.claveConf.equals(this.usuarioDto.getClave());
  }

}
