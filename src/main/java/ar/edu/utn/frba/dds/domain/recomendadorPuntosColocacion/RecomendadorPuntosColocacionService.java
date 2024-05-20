package ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion;

import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.ArrayList;

public interface RecomendadorPuntosColocacionService {
  @GET()
  Call<ListadoUbicaciones> puntosRecomendados(@Query("latitud") float latitud, @Query("longitud") float longitud, @Query("radio") float radio);
}
