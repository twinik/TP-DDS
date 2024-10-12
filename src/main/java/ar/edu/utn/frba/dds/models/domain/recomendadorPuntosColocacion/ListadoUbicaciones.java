package ar.edu.utn.frba.dds.models.domain.recomendadorPuntosColocacion;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

/**
 * Representa el listado de ubicaciones.
 */
@Getter
@Setter
public class ListadoUbicaciones {
    ArrayList<PuntoColocacion> listadoUbicaciones;
}
