package ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion;

import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import lombok.Getter;
import lombok.Setter;

/**
 * RecomendadorDePuntosDeColocacion class permite recomendar puntos de colocacion.
 */
@Setter
@Getter
public class RecomendadorDePuntosDeColocacion {

  private RecomendadorDePuntosAdapter recomendadorDePuntosAdapter;

  /**
   * Recomienda una ubicacion.
   */
  public ListadoUbicaciones recomendarUbicacion(Ubicacion punto, Float radio) {
    return recomendadorDePuntosAdapter.recomendarUbicacion(punto, radio);
  }

}