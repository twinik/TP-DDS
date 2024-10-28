package ar.edu.utn.frba.dds.models.messageFactory;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;

public class MensajeViandasRestantesFactory {
  public static String generarMensaje(Heladera heladera) {
    return "Queda(n) " + heladera.getViandas() + " vianda(s) en la heladera: " + heladera.getNombre();
  }
}
