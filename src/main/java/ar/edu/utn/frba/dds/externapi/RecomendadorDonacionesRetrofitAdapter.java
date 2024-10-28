package ar.edu.utn.frba.dds.externapi;

import ar.edu.utn.frba.dds.externapi.recomendaciones.Recomendacion;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.util.List;

/**
 * Nos permite obtener recomendaciones de donaciones para un usuario.
 */
public class RecomendadorDonacionesRetrofitAdapter implements IRecomendacionDonacionesAdapter {

  private static RecomendadorDonacionesRetrofitAdapter instance = null;
  private static String API_URL = null;
  private Retrofit retrofit;

  private RecomendadorDonacionesRetrofitAdapter() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static RecomendadorDonacionesRetrofitAdapter getInstance() throws IOException {
    if (instance == null) {
      API_URL = new ConfigReader("config.properties").getProperty("API_URL_2");
      instance = new RecomendadorDonacionesRetrofitAdapter();
    }
    return instance;
  }

  @Override
  public Recomendacion obtenerRecomendacion(String id) {
    RecomendadorDonacionesService service = this.retrofit.create(RecomendadorDonacionesService.class);
    Call<Recomendacion> requestRecomendacion = service.obtenerRecomendacion(id);
    Recomendacion recomendacion;
    try {
      recomendacion = requestRecomendacion.execute().body();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return recomendacion;
  }

  @Override
  public List<Recomendacion> listarRecomendaciones(String etiquetaProvincia, String etiquetaLocalidad) {
    RecomendadorDonacionesService service = this.retrofit.create(RecomendadorDonacionesService.class);
    Call<List<Recomendacion>> requestRecomendaciones = service.listarRecomendaciones(etiquetaProvincia, etiquetaLocalidad);
    List<Recomendacion> recomendaciones;
    try {
      recomendaciones = requestRecomendaciones.execute().body();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return recomendaciones;
  }
}