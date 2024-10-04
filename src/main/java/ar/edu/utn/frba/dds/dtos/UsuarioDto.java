package ar.edu.utn.frba.dds.dtos;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioDto {
  private String email;
  private String clave;

  public static UsuarioDto of (Context context){
    return new UsuarioDto(context.formParam("email"),context.formParam("pass"));
  }

}
