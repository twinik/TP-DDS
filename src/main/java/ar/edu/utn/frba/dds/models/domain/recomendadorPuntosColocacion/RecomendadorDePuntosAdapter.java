package ar.edu.utn.frba.dds.models.domain.recomendadorPuntosColocacion;

import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;

public interface RecomendadorDePuntosAdapter {
  ListadoUbicaciones recomendarUbicacion(Ubicacion punto, Float radio);
}
