package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Alerta Class representa
 */
public class Alerta extends Incidente {
    private TipoAlerta tipoAlerta;

    public Alerta(Heladera heladera, LocalDateTime timestamp, TipoAlerta tipoAlerta) {
        super(heladera, timestamp);
        this.tipoAlerta = tipoAlerta;
    }

    public Alerta(TipoAlerta tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }
}