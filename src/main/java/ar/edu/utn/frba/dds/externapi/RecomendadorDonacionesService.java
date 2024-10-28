package ar.edu.utn.frba.dds.externapi;

import ar.edu.utn.frba.dds.externapi.recomendaciones.Recomendacion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import java.util.List;

/**
 * RecomendacionDonacionesService class permite obtener las recomendaciones de donaciones.
 */
public interface RecomendadorDonacionesService {
  @GET("/api/punto-donacion")
  Call<List<Recomendacion>> listarRecomendaciones(@Query("provincia") String etiquetaprovincia, @Query("localidad") String etiquetalocalidad);

  @GET("/api/punto-donacion/{id}")
  Call<Recomendacion> obtenerRecomendacion(@Path("id") String id);
}
