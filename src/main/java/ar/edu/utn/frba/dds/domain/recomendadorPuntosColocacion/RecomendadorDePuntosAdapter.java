package ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion;

import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import java.io.IOException;

public interface RecomendadorDePuntosAdapter {
  ListadoUbicaciones recomendarUbicacion(Ubicacion punto, Float radio);
}
