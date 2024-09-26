package ar.edu.utn.frba.dds.models.messageFactory;

import static java.lang.String.format;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.tecnicos.Tecnico;
import java.time.LocalDateTime;

public class MensajeTecnicosIncidenteFactory {
    public static String generarMensaje(Tecnico tecnicoAContactar, Heladera heladera, LocalDateTime timestamp) {
        return format("Hola %s se rompio la heladera %s a las %s y necesitamos que por favor venga a repararla",
                tecnicoAContactar.getNombre(), heladera.getNombre(), timestamp.toString());
    }
}
