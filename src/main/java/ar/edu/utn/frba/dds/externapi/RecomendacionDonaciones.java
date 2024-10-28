package ar.edu.utn.frba.dds.externapi;

import ar.edu.utn.frba.dds.externapi.recomendaciones.Recomendacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecomendacionDonaciones {

  private IRecomendacionDonacionesAdapter adapter;


  public Recomendacion obtenerRecomendacion(String id) {
    return adapter.obtenerRecomendacion(id);
  }

  public List<Recomendacion> listarRecomendaciones(String etiquetaProvincia, String etiquetaLocalidad) {
    return adapter.listarRecomendaciones(etiquetaProvincia, etiquetaLocalidad);
  }
}
