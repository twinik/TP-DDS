package ar.edu.utn.frba.dds.dtos.colaboraciones;

import ar.edu.utn.frba.dds.models.domain.recomendadorPuntosColocacion.PuntoColocacion;
import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class RecomendacionColocacionDto {
  private List<Float> coords;
  Float latitud;
  Float longitud;
  String nombre;
  String calle;
  Integer altura;

  public static RecomendacionColocacionDto fromPunto(PuntoColocacion punto) {
    return RecomendacionColocacionDto.builder().latitud(punto.getUbicacion().getLatitud())
        .longitud(punto.getUbicacion().getLongitud())
        .coords(List.of(punto.getUbicacion().getLatitud(), punto.getUbicacion().getLongitud()))
        .nombre(punto.getNombre())
        .calle(punto.getDireccion().getCalle())
        .altura(punto.getDireccion().getAltura()).build();
  }
}
