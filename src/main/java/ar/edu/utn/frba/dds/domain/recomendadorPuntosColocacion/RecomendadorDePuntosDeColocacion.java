package ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion;

import ar.edu.utn.frba.dds.domain.helpers.ConfigReader;
import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

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