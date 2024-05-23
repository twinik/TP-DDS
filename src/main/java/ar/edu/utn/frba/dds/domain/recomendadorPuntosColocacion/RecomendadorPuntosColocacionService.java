package ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * RecomendadorPuntosColocacionService class permite obtener los puntos de colocacion recomendados.
 */
public interface RecomendadorPuntosColocacionService {
  @GET("/puntos")
  Call<ListadoUbicaciones> puntosRecomendados(@Query("latitud") float latitud, @Query("longitud") float longitud, @Query("radio") float radio);
}
