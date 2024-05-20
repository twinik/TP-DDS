package ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion;

import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public class RecomendadorDePuntosDeColocacion {

  private static RecomendadorDePuntosDeColocacion instance = null;
  private static final String API_URL = "usarconfig.com";
  private Retrofit retrofit;

  public RecomendadorDePuntosDeColocacion() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static RecomendadorDePuntosDeColocacion getInstance() {
    if (instance == null) {
      instance = new RecomendadorDePuntosDeColocacion();
    }
    return instance;
  }

  /**
   * @param punto
   * @param radio
   * @return
   */
  public ListadoUbicaciones recomendarUbicacion(Ubicacion punto, Float radio) throws IOException {
    RecomendadorPuntosColocacionService service = this.retrofit.create(RecomendadorPuntosColocacionService.class);
    Call<ListadoUbicaciones> requestRecomendacionPuntos = service.puntosRecomendados(punto.getLatitud(), punto.getLongitud(), radio);
    Response<ListadoUbicaciones> response = requestRecomendacionPuntos.execute();
    return response.body();
  }

}