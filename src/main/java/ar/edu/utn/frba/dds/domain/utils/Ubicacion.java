package ar.edu.utn.frba.dds.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ubicacion class permite representar una ubicacion.
 */
@AllArgsConstructor
@Getter
public class Ubicacion {
    private Float latitud;
    private Float longitud;
}