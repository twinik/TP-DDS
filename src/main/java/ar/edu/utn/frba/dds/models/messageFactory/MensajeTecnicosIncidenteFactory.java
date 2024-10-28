package ar.edu.utn.frba.dds.models.messageFactory;

import static java.lang.String.format;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.tecnicos.Tecnico;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MensajeTecnicosIncidenteFactory {
  public static String generarMensaje(Tecnico tecnicoAContactar, Heladera heladera, LocalDateTime timestamp) {
    return format("Estimado %s, la heladera %s se ha averiado a las %s. Le solicitamos amablemente que acuda a repararla a la brevedad posible.",
        tecnicoAContactar.getNombre(), heladera.getNombre(), timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")));
  }
}
