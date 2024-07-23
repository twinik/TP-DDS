package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.excepciones.NoAutorizadoParaAbrirHeladeraException;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.domain.tarjetas.TarjetaColaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

/**
 * Clase que representa la apertura de de una heladera
 */
@Getter
@Setter
@AllArgsConstructor
public class AperturaHeladera {
    private long id;
    private SolicitudAperturaHeladera solicitud;
    private LocalDateTime timestamp;
    private Heladera heladera;

    public AperturaHeladera(SolicitudAperturaHeladera solicitud, LocalDateTime timestamp, Heladera heladera) {
        this.solicitud = solicitud;
        this.timestamp = timestamp;
        this.heladera = heladera;
    }

    public static AperturaHeladera of(SolicitudAperturaHeladera solicitud, LocalDateTime timestamp, Heladera heladera){
        return new AperturaHeladera(solicitud,timestamp,heladera);
    }
}