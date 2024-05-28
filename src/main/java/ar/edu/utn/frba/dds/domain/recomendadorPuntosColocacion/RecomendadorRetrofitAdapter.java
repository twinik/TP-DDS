package ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion;

import ar.edu.utn.frba.dds.domain.excepciones.FallaAlConsumirApiException;
import ar.edu.utn.frba.dds.domain.helpers.ConfigReader;
import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class RecomendadorRetrofitAdapter implements RecomendadorDePuntosAdapter {

  private static RecomendadorRetrofitAdapter instance = null;
  private static String API_URL = null;
  private Retrofit retrofit;

  private RecomendadorRetrofitAdapter() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static RecomendadorRetrofitAdapter getInstance() throws IOException {
    if (instance == null) {
      API_URL = new ConfigReader("api-recomendador-ubicaciones-config.properties").getProperty("API_URL");
      instance = new RecomendadorRetrofitAdapter();
    }
    return instance;
  }

  /**
   *
   * @param punto punto donde esta usted y quisiera colocar la heladera
   * @param radio cuantos killoemtros desde tu punto de referencia toleraria alejarse
   * @return el listado de posibles ubicaciones a paartir de los parametros
   */
  public ListadoUbicaciones recomendarUbicacion(Ubicacion punto, Float radio) {
    RecomendadorPuntosColocacionService service = this.retrofit.create(RecomendadorPuntosColocacionService.class);
    Call<ListadoUbicaciones> requestRecomendacionPuntos = service.puntosRecomendados(punto.getLatitud(), punto.getLongitud(), radio);
    Response<ListadoUbicaciones> response;
    try {
      response = requestRecomendacionPuntos.execute();
    } catch (IOException e) {
      throw new FallaAlConsumirApiException(e);
    }
    return response.body();
  }
}
