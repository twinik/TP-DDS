package ar.edu.utn.frba.dds.externapi.recomendaciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RecomendacionDto {

  private String id;
  private List<Float> coords;
  private String nombre;
  private String calle;
  private Integer altura;

  public static RecomendacionDto fromRecomendacion(Recomendacion d) {
    List<Float> coords = new ArrayList<>();
    coords.add(Float.parseFloat(d.getLatitud()));
    coords.add(Float.parseFloat(d.getLongitud()));
    return new RecomendacionDto(d.getId(), coords, d.getNombre(), d.getCalle(), d.getAltura());
  }
}
