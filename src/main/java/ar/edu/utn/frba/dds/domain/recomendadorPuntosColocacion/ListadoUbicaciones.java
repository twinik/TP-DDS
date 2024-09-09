package ar.edu.utn.frba.dds.domain.recomendadorPuntosColocacion;

import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

/**
 * Representa el listado de ubicaciones.
 */
@Getter
@Setter
public class ListadoUbicaciones {
    ArrayList<Ubicacion> listadoUbicaciones;
}
