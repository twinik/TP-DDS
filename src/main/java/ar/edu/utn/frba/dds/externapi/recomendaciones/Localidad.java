package ar.edu.utn.frba.dds.externapi.recomendaciones;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Localidad {

  private Integer id;
  private String nombre;
  private String etiqueta;

  public static String of(Context ctx) {
    return ctx.formParam("localidad");
  }
}