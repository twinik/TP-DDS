package ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion;

import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa el listado de ubicaciones.
 */
@Getter
@Setter
public class ListadoUbicaciones {
  ArrayList<Ubicacion> listadoUbicaciones;
}
