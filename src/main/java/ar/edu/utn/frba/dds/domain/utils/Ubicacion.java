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

    public float calcularDistanciaHasta(Ubicacion ubicacion) {
        float radioTierra = 6371; // en kilometros
        float dLatitud = (float) Math.toRadians(ubicacion.getLatitud() - this.getLatitud());
        float dLongitud = (float) Math.toRadians(ubicacion.getLongitud() - this.getLongitud());
        float a = (float) (Math.sin(dLatitud / 2) * Math.sin(dLatitud / 2)
                        + Math.cos(Math.toRadians(this.getLatitud())) * Math.cos(Math.toRadians(ubicacion.getLatitud()))
                                * Math.sin(dLongitud / 2) * Math.sin(dLongitud / 2));
        float c = (float) (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));
        return radioTierra * c;
    }
}