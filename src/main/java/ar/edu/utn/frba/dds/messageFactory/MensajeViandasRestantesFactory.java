package ar.edu.utn.frba.dds.messageFactory;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;

public class MensajeViandasRestantesFactory {
  public static String generarMensaje(Heladera heladera) {
    return "Quedan " + heladera.getViandas().size() + " viandas en la heladera " + heladera.getNombre();
  }
}
