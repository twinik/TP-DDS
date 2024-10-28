package ar.edu.utn.frba.dds.externapi;

import ar.edu.utn.frba.dds.externapi.recomendaciones.Recomendacion;
import java.util.List;

public interface IRecomendacionDonacionesAdapter {

  Recomendacion obtenerRecomendacion(String id);

  List<Recomendacion> listarRecomendaciones(String etiquetaProvincia, String etiquetaLocalidad);
}
