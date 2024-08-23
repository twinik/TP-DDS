package ar.edu.utn.frba.dds.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * Ubicacion class permite representar una ubicacion.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "ubicacion")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitud", nullable = false)
    private Float latitud;

    @Column(name = "longitud", nullable = false)
    private Float longitud;

    public Ubicacion(Float latitud, Float longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

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