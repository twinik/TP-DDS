package ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion;

import ar.edu.utn.frba.dds.domain.helpers.ConfigReader;
import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

/**
 * RecomendadorDePuntosDeColocacion class permite recomendar puntos de colocacion.
 */
public class RecomendadorDePuntosDeColocacion {

  private static RecomendadorDePuntosDeColocacion instance = null;
  private static String API_URL = null;
  private Retrofit retrofit;

  private RecomendadorDePuntosDeColocacion() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static RecomendadorDePuntosDeColocacion getInstance() throws IOException {
    if (instance == null) {
      API_URL = new ConfigReader("api-recomendador-ubicaciones-config.properties").getProperty("API_URL");
      instance = new RecomendadorDePuntosDeColocacion();
    }
    return instance;
  }

  /**
   * Recomienda una ubicacion.
   */
  public ListadoUbicaciones recomendarUbicacion(Ubicacion punto, Float radio) throws IOException {
    RecomendadorPuntosColocacionService service = this.retrofit.create(RecomendadorPuntosColocacionService.class);
    Call<ListadoUbicaciones> requestRecomendacionPuntos = service.puntosRecomendados(punto.getLatitud(), punto.getLongitud(), radio);
    Response<ListadoUbicaciones> response = requestRecomendacionPuntos.execute();
    return response.body();
  }

}